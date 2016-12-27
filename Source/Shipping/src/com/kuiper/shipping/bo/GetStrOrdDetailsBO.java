package com.kuiper.shipping.bo;

import java.util.List;

import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.dao.GetStrOrdDetailsDAO;
import com.kuiper.shipping.model.StoreOrder;
import com.kuiper.shipping.model.StoreOrderLine;

public class GetStrOrdDetailsBO {

	public static List<StoreOrder> process() {
		List<StoreOrder> colst = GetStrOrdDetailsDAO.getCOList();

		for (StoreOrder co : colst) {
			for (StoreOrderLine coline : co.getStoreOrderLinelst()) {
				if (coline.getDue_qty() == 0 && co.getOrder_status() != Constant.SHIPPED) {
					coline.setDue_qty(coline.getOrdered_qty());
				}
			}
		}
		return colst;
	}

}
