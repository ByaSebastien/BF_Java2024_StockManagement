package be.bstorm.bf_java2024_stockmanagement.pl.models;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Stock;

import java.util.UUID;

public record ArticleDTO(
        UUID id,
        String designation,
        double unitPriceExcludingTax,
        double unitPriceIncludingTax,
        String picture,
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
                a.getPicture(),
                a.getCategory().getDesignation(),
                stock.getCurrentQuantity()
        );
    }
}
