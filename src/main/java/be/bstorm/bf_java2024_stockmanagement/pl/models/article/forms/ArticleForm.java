package be.bstorm.bf_java2024_stockmanagement.pl.models.article.forms;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class ArticleForm {

    private String designation;
    private double unitPriceExcludingTax;
    private VAT vat;
    private MultipartFile image;
    private UUID categoryId;

    public Article toArticle() {
        return new Article(
                this.designation,
                (long) unitPriceExcludingTax * 100,
                vat
        );
    }
}
