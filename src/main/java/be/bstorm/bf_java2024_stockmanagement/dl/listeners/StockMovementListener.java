package be.bstorm.bf_java2024_stockmanagement.dl.listeners;

import be.bstorm.bf_java2024_stockmanagement.dal.repositories.StockRepository;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Stock;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.StockMovement;
import jakarta.persistence.PostPersist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StockMovementListener {

    private final StockRepository stockRepository;

    @PostPersist
    public void updateStock(StockMovement movement) {

        Stock stock = Objects.requireNonNullElse(
                movement.getArticle().getStock(),
                new Stock(UUID.randomUUID(), 0, movement.getArticle())
        );

        switch (movement.getMovementType()) {
            case STOCK_IN:
            case STOCK_POSITIVE_CORRECTION:
            case STOCK_RECALL:
                stock.addQuantity(movement.getQuantity());
                break;
            case STOCK_OUT:
            case STOCK_NEGATIVE_CORRECTION:
            case STOCK_RETURN:
            case STOCK_MISSING:
                stock.subtractQuantity(movement.getQuantity());
                break;
        }

        stockRepository.save(stock);
    }
}
