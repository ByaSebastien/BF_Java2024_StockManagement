package be.bstorm.bf_java2024_stockmanagement.pl.controllers;

import be.bstorm.bf_java2024_stockmanagement.bll.services.StockService;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Stock;
import be.bstorm.bf_java2024_stockmanagement.pl.models.ArticleDTO;
import be.bstorm.bf_java2024_stockmanagement.pl.models.ArticleDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final StockService stockService;

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
}
