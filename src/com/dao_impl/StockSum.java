package com.dao_impl;

import com.modelDataCK.StockModel;
import com.modelDataCK.TotalDataHNXModel;
import com.modelDataCK.TotalDataHOSEModel;
import com.dao.IGetDataDAO;
import com.dao.IStockSum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockSum extends GetDataDAO implements IStockSum {
    //    String[] listFile = {"HNX30-", "HNX-", "HNXCON-", "HNXFIN-", "HNXLCAP-",
//            "HNXMAN-", "HNXMSCAP-", "HOSE-","VN30-", "VN100-", "VNALL-",
//            "VNMID-", "VNX50-"};
    String[] listFile = {"HNX-", "HOSE-","VN30-", "VN100-"};

    @Override
    public List<StockModel> sumList(String date) {

        List<StockModel> listStock = new ArrayList<>();

        for (int i = 0; i < listFile.length; i++) {
            IGetDataDAO getDataDAO = new GetDataDAO();
            String file = "data\\" + date + "052020\\" + listFile[i] + date + "05.txt";
            listStock.addAll(getDataDAO.getDataToList(file));
        }
        return listStock;
    }

    @Override
    public List<TotalDataHNXModel> sumIndexHNX(String date) throws IOException {
//        String[] listFile = {"HNX30-", "HNX-", "HNXCON-", "HNXFIN-", "HNXLCAP-",
//                "HNXMAN-", "HNXMSCAP-", "HOSE-","VN30-", "VN100-", "VNALL-",
//                "VNMID-", "VNX50-"};
        List<TotalDataHNXModel> hnxModelList = new ArrayList<>();
        for(int i = 0; i < listFile.length; i++){
            if(listFile[i].contains("HNX")){
                String file = "data\\" + date + "052020\\" + listFile[i] + date + "05.txt";
                IGetDataDAO getDataDAO = new GetDataDAO();
                hnxModelList.add(((GetDataDAO) getDataDAO).getDataToHNX(file));
            }
        }
        return hnxModelList;
    }

    @Override
    public List<TotalDataHOSEModel> sumIndexNotHNX(String date) throws IOException {
//        String[] listFile = {"HNX30-", "HNX-", "HNXCON-", "HNXFIN-", "HNXLCAP-",
//                "HNXMAN-", "HNXMSCAP-", "HOSE-","VN30-", "VN100-", "VNALL-",
//                "VNMID-", "VNX50-"};
        List<TotalDataHOSEModel> modelList = new ArrayList<>();
        for(int i = 0; i < listFile.length; i++){
            if(!listFile[i].contains("HNX")){
                String file = "data\\" + date + "052020\\" + listFile[i] + date + "05.txt";
                IGetDataDAO getDataDAO = new GetDataDAO();
                modelList.add(((GetDataDAO) getDataDAO).getDataToModel(file));
            }
        }
        return modelList;
    }
}
