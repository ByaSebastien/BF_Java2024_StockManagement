package be.bstorm.bf_java2024_stockmanagement.dl.entities.order;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Supplier;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true) @ToString(callSuper=true)
public class SupplierOrder extends Order{

    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;

    public SupplierOrder(UUID id, LocalDateTime orderDate, String comment, Supplier supplier) {
        super(id, orderDate, comment);
        this.supplier = supplier;
    }
}
