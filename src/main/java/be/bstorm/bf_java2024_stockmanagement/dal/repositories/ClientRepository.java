package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
}
