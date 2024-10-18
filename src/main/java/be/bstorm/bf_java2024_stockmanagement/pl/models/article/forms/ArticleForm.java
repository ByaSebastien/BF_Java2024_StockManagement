package be.bstorm.bf_java2024_stockmanagement.pl.models.article.forms;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import be.bstorm.bf_java2024_stockmanagement.pl.validators.annotations.ImageFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class ArticleForm {

    @NotBlank(message = "Required field")
    private String designation;
    @Min(0)
    private double unitPriceExcludingTax;
    private VAT vat;
    @ImageFormat
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
