package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true, of = {"designation", "unitPriceExcludingTax", "vat", "category"})
@ToString(callSuper = true, of = {"designation", "unitPriceExcludingTax", "vat", "category"})
public class Article extends BaseEntity {

    @Setter
    @Column(nullable = false, unique = true,length = 80)
    private String designation;

    @Setter
    @Column(nullable = false)
    @Range(min = 0L)
    private long unitPriceExcludingTax;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VAT vat;

    @Setter
    @Column(nullable = true)
    private String picture;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToOne(mappedBy = "article",fetch = FetchType.EAGER)
    private Stock stock;

    public Article(String designation, long unitPriceExcludingTax, VAT vat) {
        this.designation = designation;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.vat = vat;
    }

    public Article(UUID id, String designation, long unitPriceExcludingTax, VAT vat, String picture, Category category) {
        super(id);
        this.designation = designation;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.vat = vat;
        this.picture = picture;
        this.category = category;
    }

    protected void setStock(Stock stock) {
        this.stock = stock;
    }

    public long getUnitPriceIncludingTax() {
        return getUnitPriceExcludingTax() + getAddedValue();
    }

    public long getAddedValue() {

        BigDecimal vat = BigDecimal.valueOf(this.vat.value,2);
        BigDecimal priceTTE = BigDecimal.valueOf(this.unitPriceExcludingTax);
        BigDecimal addedValue = priceTTE.multiply(vat);
        return addedValue.setScale(0,RoundingMode.HALF_UP).longValue();
    }
}
