package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Category extends BaseEntity {

    @Column(nullable = false,unique = true,length = 80)
    private String designation;

    public Category(UUID id, String designation) {
        super(id);
        this.designation = designation;
    }
}
