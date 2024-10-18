package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Stock extends BaseEntity{

    @Column(nullable = false)
    @Setter
    private int currentQuantity;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private Article article;

    public Stock(UUID id, int currentQuantity, Article article) {
        super(id);
        this.currentQuantity = currentQuantity;
        this.setArticle(article);
    }

    private void setArticle(Article article) {
        this.article = article;
        article.setStock(this);
    }
}
