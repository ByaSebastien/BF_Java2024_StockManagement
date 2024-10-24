package be.bstorm.bf_java2024_stockmanagement.pl.controllers;

import be.bstorm.bf_java2024_stockmanagement.bll.services.ArticleService;
import be.bstorm.bf_java2024_stockmanagement.bll.services.CategoryService;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Category;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import be.bstorm.bf_java2024_stockmanagement.pl.models.article.dtos.ArticleDTO;
import be.bstorm.bf_java2024_stockmanagement.pl.models.article.dtos.ArticleDetailsDTO;
import be.bstorm.bf_java2024_stockmanagement.pl.models.article.forms.ArticleForm;
import be.bstorm.bf_java2024_stockmanagement.pl.models.article.forms.ArticleUpdateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

    @GetMapping
    public String getArticles(Model model) {

        List<ArticleDTO> articles = articleService.findAll().stream()
                .map(ArticleDTO::fromArticle)
                .toList();
        model.addAttribute("articles", articles);
        return "article/index";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable UUID id, Model model) {

        try{
            ArticleDetailsDTO dto = ArticleDetailsDTO.fromArticle(articleService.findById(id));
            model.addAttribute("article", dto);
            return "article/details";
        } catch (NoSuchElementException e){
            return "error/error404";
        }
    }

    @GetMapping("/create")
    public String createArticle(Model model) {

        model.addAttribute("articleForm", new ArticleForm());
        model.addAttribute("vatOptions", VAT.values());
        model.addAttribute("categories",categoryService.findAll());
        return "article/create";
    }

    @PostMapping("/create")
    public String createArticle(
            @Valid @ModelAttribute ArticleForm articleForm,
            BindingResult bindingResult,
            Model model
    ) {

        if(bindingResult.hasErrors()) {

            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

            model.addAttribute("errors", errors);
            model.addAttribute("articleForm", articleForm);
            model.addAttribute("vatOptions", VAT.values());
            model.addAttribute("categories",categoryService.findAll());
            return "article/create";
        }

        Category category = categoryService.findById(articleForm.getCategoryId());
        Article article = articleForm.toArticle();
        article.setCategory(category);
        articleService.save(article ,articleForm.getImage());
        return "redirect:/article";
    }

    @GetMapping("/update/{id}")
    public String updateArticle(@PathVariable UUID id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("articleForm", ArticleUpdateForm.fromArticle(article));
        model.addAttribute("vatOptions", VAT.values());
        model.addAttribute("categories",categoryService.findAll());
        return "article/update";
    }

    @PostMapping("/update")
    public String updateArticle(
        @Valid @ModelAttribute ArticleUpdateForm articleForm,
        BindingResult bindingResult,
        Model model
    ){
        if(bindingResult.hasErrors()) {

            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

            model.addAttribute("errors", errors);
            model.addAttribute("articleForm", articleForm);
            model.addAttribute("vatOptions", VAT.values());
            model.addAttribute("categories",categoryService.findAll());
            return "article/update";
        }

        Article article = articleForm.toArticle();
        article.setCategory(categoryService.findById(articleForm.getCategoryId()));
        articleService.update(article,articleForm.getImage());
        return "redirect:/article";
    }

    @PostMapping("/delete/{id}")
    public String deleteArticle(@PathVariable UUID id) {
        articleService.delete(id);
        return "redirect:/article";
    }
}
