package be.bstorm.bf_java2024_stockmanagement.bll.services.impls;

import be.bstorm.bf_java2024_stockmanagement.bll.services.StockService;
import be.bstorm.bf_java2024_stockmanagement.dal.repositories.StockRepository;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockByArticleId(UUID articleId) {
        return stockRepository.findByArticleId(articleId).orElseThrow();
    }
}
