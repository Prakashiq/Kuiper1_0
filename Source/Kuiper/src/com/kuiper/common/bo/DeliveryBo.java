package com.kuiper.common.bo;

import java.util.ArrayList;
import java.util.List;

import com.kuiper.common.dao.CommonDeliveryDao;
import com.kuiper.common.model.PurchaseOrder;

public class DeliveryBo {
	public static List<Integer> process() {
		
		List<Integer> polist= new ArrayList<Integer>();
		
		List<PurchaseOrder> polst = CommonDeliveryDao.getPOList();
		
		for (PurchaseOrder po : polst)
		{
			polist.add(po.get_id());
		}
		
		return polist;
	}

}
