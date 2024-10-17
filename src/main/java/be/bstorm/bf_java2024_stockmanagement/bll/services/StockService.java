package be.bstorm.bf_java2024_stockmanagement.bll.services;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Stock;

import java.util.List;

public interface StockService {

    List<Stock> getStocks();
}
