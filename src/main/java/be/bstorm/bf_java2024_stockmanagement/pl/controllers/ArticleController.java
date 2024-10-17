package be.bstorm.bf_java2024_stockmanagement.pl.controllers;

import be.bstorm.bf_java2024_stockmanagement.bll.services.StockService;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Stock;
import be.bstorm.bf_java2024_stockmanagement.pl.models.ArticleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
