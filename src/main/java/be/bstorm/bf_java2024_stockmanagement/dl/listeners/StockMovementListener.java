package be.bstorm.bf_java2024_stockmanagement.dl.listeners;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Stock;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.StockMovement;
import jakarta.persistence.PostPersist;

import java.util.Objects;
import java.util.UUID;

public class StockMovementListener {

    @PostPersist
    public void updateStock(StockMovement movement){

        Stock stock = Objects.requireNonNullElse(
                movement.getArticle().getStock(),
                new Stock(UUID.randomUUID(),0,movement.getArticle())
        );


    }
}
