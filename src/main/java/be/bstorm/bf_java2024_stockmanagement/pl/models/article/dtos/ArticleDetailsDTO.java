package be.bstorm.bf_java2024_stockmanagement.pl.models.article.dtos;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;

import java.util.UUID;

public record ArticleDetailsDTO(
        UUID id,
        String designation,
        double unitPriceExcludingTax,
        double unitPriceIncludingTax,
        double addedValue,
        VAT vat,
        String picture,
        String category,
        int quantity
) {

    public static ArticleDetailsDTO fromArticle(Article a) {
        return new ArticleDetailsDTO(
                a.getId(),
                a.getDesignation(),
                a.getUnitPriceExcludingTax() / 100D,
                a.getUnitPriceIncludingTax() /100D,
                a.getAddedValue() / 100D,
                a.getVat(),
                a.getPicture(),
                a.getCategory().getDesignation(),
                a.getStock() == null ? 0 : a.getStock().getCurrentQuantity()
        );
    }
}
