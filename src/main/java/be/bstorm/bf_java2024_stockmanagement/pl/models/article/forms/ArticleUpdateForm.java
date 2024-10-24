package be.bstorm.bf_java2024_stockmanagement.pl.models.article.forms;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class ArticleUpdateForm extends ArticleForm{

    private UUID id;
    private String imageUrl;

    public ArticleUpdateForm(UUID id,String designation, double unitPriceExcludingTax, VAT vat, UUID categoryId, String imageUrl) {
        super(designation, unitPriceExcludingTax, vat, categoryId);
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public static ArticleUpdateForm fromArticle(Article article) {
        return new ArticleUpdateForm(
                    article.getId(),
                    article.getDesignation(),
                    article.getUnitPriceExcludingTax() / 100D,
                    article.getVat(),
                    article.getCategory().getId(),
                    article.getPicture()
                );
    }

    @Override
    public Article toArticle() {
        return new Article(
                this.id,
                this.getDesignation(),
                (long) (this.getUnitPriceExcludingTax() * 100),
                this.getVat()
        );
    }
}
