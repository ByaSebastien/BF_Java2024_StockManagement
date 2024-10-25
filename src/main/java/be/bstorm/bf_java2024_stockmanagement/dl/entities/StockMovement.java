package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import be.bstorm.bf_java2024_stockmanagement.dl.enums.StockMovementType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class StockMovement extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StockMovementType movementType;

    @Column(nullable = false)
    @Range(min = 0L)
    private int quantity;

    @Column(nullable = false)
    private LocalDateTime movementDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Article article;

    public StockMovement(UUID id, StockMovementType movementType, int quantity, LocalDateTime movementDate, Article article) {
        super(id);
        this.movementType = movementType;
        this.quantity = quantity;
        this.movementDate = movementDate;
        this.article = article;
    }
}
