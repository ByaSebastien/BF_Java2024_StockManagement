package be.bstorm.bf_java2024_stockmanagement.pl.models.article.dtos;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Stock;

import java.util.UUID;

public record ArticleDTO(
        UUID id,
        String designation,
        double unitPriceExcludingTax,
        double unitPriceIncludingTax,
        String category,
        int quantity
) {

    public static ArticleDTO fromStock(Stock stock) {
        Article a = stock.getArticle();
        return new ArticleDTO(
                a.getId(),
                a.getDesignation(),
                a.getUnitPriceExcludingTax() / 100D,
                a.getUnitPriceIncludingTax() / 100D,
                a.getCategory().getDesignation(),
                stock.getCurrentQuantity()
        );
    }
}
