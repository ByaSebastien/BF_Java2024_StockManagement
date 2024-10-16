package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Role extends BaseEntity {

    @Column(unique = true,nullable = false,length = 20)
    private String name;

    public Role(UUID id, String name) {
        super(id);
        this.name = name;
    }
}
