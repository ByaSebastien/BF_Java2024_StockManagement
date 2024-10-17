package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Article extends BaseEntity {

    @Getter @Setter
    @Column(nullable = false, unique = true,length = 80)
    private String designation;

    @Getter @Setter
    @Column(nullable = false)
    @Min(0L)
    private long unitPriceExcludingTax;

    @Getter @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VAT vat;

    @Getter @Setter
    @Column(nullable = true)
    private String picture;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    public Article(UUID id, String designation, long unitPriceExcludingTax, VAT vat, String picture, Category category) {
        super(id);
        this.designation = designation;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.vat = vat;
        this.picture = picture;
        this.category = category;
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
