package be.bstorm.bf_java2024_stockmanagement.bll.services;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Stock;

import java.util.List;
import java.util.UUID;

public interface StockService {

    List<Stock> getStocks();
    Stock getStockByArticleId(UUID articleId);
}
