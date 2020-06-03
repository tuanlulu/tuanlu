package com.process;

import java.util.Comparator;

import com.modelDataCK.StockModel;

public class SortByPriceLowHistory implements Comparator<StockModel>{
	public int compare (StockModel a, StockModel b) {
		double a1 = a.getPriceLowHistory();
		double b1 = b.getPriceLowHistory();
		if (a1-b1 > 0) return 1;
		if (a1-b1 < 0) return -1;
		return 0;
	}
}