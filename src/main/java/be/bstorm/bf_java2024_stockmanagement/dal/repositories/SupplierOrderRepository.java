package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierOrderRepository extends JpaRepository<SupplierOrderRepository, UUID> {
}
