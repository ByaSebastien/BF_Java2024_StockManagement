package be.bstorm.bf_java2024_stockmanagement.dl.entities.order;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class ClientOrder extends Order {

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public ClientOrder(UUID id, LocalDateTime orderDate, String comment, Client client) {
        super(id, orderDate, comment);
        this.client = client;
    }
}
