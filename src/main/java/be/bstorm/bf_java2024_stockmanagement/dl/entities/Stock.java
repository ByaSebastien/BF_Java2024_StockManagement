package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Stock extends BaseEntity{

    @Column(nullable = false)
    private int currentQuantity;

    @OneToOne(fetch = FetchType.EAGER)
    private Article article;

    public Stock(UUID id, int currentQuantity, Article article) {
        super(id);
        this.currentQuantity = currentQuantity;
        this.article = article;
    }
}
