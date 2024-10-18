package be.bstorm.bf_java2024_stockmanagement.pl.controllers;

import be.bstorm.bf_java2024_stockmanagement.bll.services.ArticleService;
import be.bstorm.bf_java2024_stockmanagement.bll.services.CategoryService;
import be.bstorm.bf_java2024_stockmanagement.bll.services.StockService;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Category;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import be.bstorm.bf_java2024_stockmanagement.pl.models.article.dtos.ArticleDTO;
import be.bstorm.bf_java2024_stockmanagement.pl.models.article.dtos.ArticleDetailsDTO;
import be.bstorm.bf_java2024_stockmanagement.pl.models.article.forms.ArticleForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final StockService stockService;
    private final ArticleService articleService;
    private final CategoryService categoryService;

    @GetMapping
    public String getArticles(Model model) {

        List<ArticleDTO> articles = stockService.getStocks().stream()
                .map(ArticleDTO::fromStock)
                .toList();
        model.addAttribute("articles", articles);
        return "article/index";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable UUID id, Model model) {

        try{
            ArticleDetailsDTO dto = ArticleDetailsDTO.fromStock(stockService.getStockByArticleId(id));
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
    public String createArticle(@ModelAttribute ArticleForm articleForm, Model model) {

        Category category = categoryService.findById(articleForm.getCategoryId());
        Article article = articleForm.toArticle();
        article.setCategory(category);
        articleService.save(article ,articleForm.getImage());
        return "redirect:/article";
    }
}
