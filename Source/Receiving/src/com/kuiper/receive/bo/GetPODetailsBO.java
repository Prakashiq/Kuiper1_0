package com.kuiper.receive.bo;

import java.util.List;

import com.kuiper.receive.constants.Constant;
import com.kuiper.receive.dao.GetPODetailsDAO;
import com.kuiper.receive.model.PurchaseOrder;
import com.kuiper.receive.model.PurchaseOrderLine;

public class GetPODetailsBO {

	public static List<PurchaseOrder> process() {
		List<PurchaseOrder> polst = GetPODetailsDAO.getPOList();

		for (PurchaseOrder po : polst) {
			for (PurchaseOrderLine poline : po.getPurchaseOrderLinelst()) {
				if (poline.getDue_qty() == 0 && po.getPo_status() != Constant.RECIVED_PO) {
					poline.setDue_qty(poline.getOrdered_qty());
				}
			}
		}
		return polst;
	}
}
