package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import be.bstorm.bf_java2024_stockmanagement.dl.enums.StockMovementType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.*;

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
    @Min(0)
    private int quantity;

    @Column(nullable = false)
    private LocalDateTime movementDate;

    public StockMovement(UUID id, StockMovementType movementType, int quantity, LocalDateTime movementDate) {
        super(id);
        this.movementType = movementType;
        this.quantity = quantity;
        this.movementDate = movementDate;
    }
}
