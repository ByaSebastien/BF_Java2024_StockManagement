package be.bstorm.bf_java2024_stockmanagement.dl.entities.order;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "order_")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Order extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = true)
    private String comment;

    public Order(UUID id, LocalDateTime orderDate, String comment) {
        super(id);
        this.orderDate = orderDate;
        this.comment = comment;
    }
}
