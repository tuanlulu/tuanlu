package com.service_impl;

import com.dao.IStockSum;
import com.dao_impl.StockSum;
import com.modelDataCK.StockModel;
import com.modelDataCK.TotalDataHNXModel;
import com.modelDataCK.TotalDataHOSEModel;
import com.service.IStockService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StockService implements IStockService {

    IStockSum stockSum = new StockSum();

    List<StockModel> modelList = new ArrayList<>();

    List<TotalDataHOSEModel> dataModels = new ArrayList<>();

    List<TotalDataHNXModel> dataHNXModels = new ArrayList<>();

    private String [] day = {"21", "22", "25", "26", "27"};

    @Override
    public List<StockModel> getDataOneDayOfCode(String date) {
        Set<StockModel> modelSet = new HashSet<>();
        modelSet.addAll(stockSum.sumList(date));
        List<StockModel> lis = new ArrayList<>();
        for(StockModel st : modelSet){
            lis.add(st);
        }
        return lis;
    }

    @Override
    public List<StockModel> getDataAllDayOfCode(String code) {

        Set<StockModel> stockModelSet = new HashSet<>();
        for(int i = 0; i<day.length; i++){
            stockModelSet.addAll(stockSum.sumList(day[i]));
        }
        for(StockModel st : stockModelSet){
            if(st.getStockCode().equalsIgnoreCase(code)){
                modelList.add(st);
            }
        }
        return modelList;
    }

    @Override
    public List<TotalDataHNXModel> getIndexHNXAllDayOfFloor(String floor) {

        try {
            for(int i = 0; i < day.length; i++){
                dataHNXModels.addAll(stockSum.sumIndexHNX(day[i]));
            }

            List<TotalDataHNXModel> dataHNXModels1 = new ArrayList<>();

            for(int j = 0; j < dataHNXModels.size(); j++){
                if(dataHNXModels.get(j).getNameExchange().equalsIgnoreCase(floor)){
                    dataHNXModels1.add(dataHNXModels.get(j));
                }
            }
            return dataHNXModels1;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TotalDataHOSEModel> getIndexNotHNXAllDayOfFloor(String floor) {

        try {
            for(int i = 0; i < day.length; i++){
                dataModels.addAll(stockSum.sumIndexNotHNX(day[i]));
            }

            List<TotalDataHOSEModel> dataModels1 = new ArrayList<>();

            for(int i = 0; i < dataModels.size(); i++){
                if(dataModels.get(i).getNameExchange().equalsIgnoreCase(floor)){
                    dataModels1.add(dataModels.get(i));
                }
            }
            return dataModels1;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


}
