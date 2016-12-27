package com.kuiper.common.bo;

import java.util.ArrayList;
import java.util.List;

import com.kuiper.common.dao.StoreOrderDao;
import com.kuiper.common.model.StoreOrder;



public class StoreOrderBo {

public static List<Integer> process() {
		
		List<Integer> colist= new ArrayList<Integer>();
		
		List<StoreOrder> colst = StoreOrderDao.getCOList();
		
		for (StoreOrder co : colst)
		{
			colist.add(co.get_id());
		}
		
		return colist;
	}

}
