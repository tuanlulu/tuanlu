package com.dao;

import com.modelDataCK.StockModel;

import java.util.List;

public interface IStockSum {
    List<StockModel> sumList(String date);
}
