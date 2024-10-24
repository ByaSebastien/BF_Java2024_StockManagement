package be.bstorm.bf_java2024_stockmanagement.pl.models.article.forms;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import be.bstorm.bf_java2024_stockmanagement.pl.validators.annotations.ImageFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ArticleForm {

    @NotBlank(message = "Required field")
    private String designation;
    @Min(0)
    private double unitPriceExcludingTax;
    private VAT vat;
    @ImageFormat
    private MultipartFile image;
    private UUID categoryId;

    public ArticleForm(String designation, double unitPriceExcludingTax, VAT vat, UUID categoryId) {
        this.designation = designation;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.vat = vat;
        this.categoryId = categoryId;
    }

    public Article toArticle() {
        return new Article(
                this.designation,
                (long) (unitPriceExcludingTax * 100),
                vat
        );
    }
}
