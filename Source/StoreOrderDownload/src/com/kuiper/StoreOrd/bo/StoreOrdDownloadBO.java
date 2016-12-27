package com.kuiper.StoreOrd.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kuiper.StoreOrd.constants.Constant;
import com.kuiper.StoreOrd.dao.DBInit;
import com.kuiper.StoreOrd.dao.InventoryLogDAO;
import com.kuiper.StoreOrd.dao.InvntLogSeqDAO;
import com.kuiper.StoreOrd.dao.StoreOrdDownloadDAO;
import com.kuiper.StoreOrd.model.InventoryLog;
import com.kuiper.StoreOrd.model.SequenceException;
import com.kuiper.StoreOrd.model.StoreOrder;
import com.kuiper.StoreOrd.model.StoreOrderLine;
import com.kuiper.StoreOrd.model.StoreOrderList;
import com.kuiper.StoreOrd.model.StoreOrder_ex;

public class StoreOrdDownloadBO {
	final static Logger logger = Logger.getLogger(StoreOrdDownloadBO.class);
	static StoreOrdDownloadDAO storeOrderDownloadDAO = null;

	public String process(StoreOrderList storeOrderLst) {
		String ret = Constant.STR_FAILURE;
		DBInit.initateDB();
		storeOrderDownloadDAO = new StoreOrdDownloadDAO();
		for (StoreOrder storeOrder : storeOrderLst.getStoreOrderList()) {

			ret = validate(storeOrder);
			if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
				
				ret = storeOrderDownloadDAO.loadstoreOrder(storeOrder);

				if (ret == Constant.STR_SUCCESS) {
					
					if (ret.equalsIgnoreCase(Constant.STR_SUCCESS))
					{
						 GsonBuilder builder = new GsonBuilder();
					     Gson gson = builder.create();
					     String jsonmsg = gson.toJson(storeOrder);
					        logger.info("BroadCastMessage :"+ jsonmsg);
						 new BroadCastMessage().sendmessage(jsonmsg);
					}

					
					InventoryLog inventoryLog = new InventoryLog();

					try {
						inventoryLog.set_id(InvntLogSeqDAO.getNextSequenceId("Inv_req_id"));
					} catch (SequenceException e) {
						e.printStackTrace();
						return Constant.SEQ_OBJ_ERR;
					}
					inventoryLog.setLast_modified_prg(storeOrder.getLast_modified_prg());
					inventoryLog.setLast_modified_ts(storeOrder.getLast_modified_ts());
					inventoryLog.setLast_modified_user(storeOrder.getLast_modified_user());
					inventoryLog.setOrder_nbr(storeOrder.get_id());
					
					for (StoreOrderLine storeOrd : storeOrder.getStoreOrderLinelst()) {
						inventoryLog.setAction(Constant.ORDER_ALLOCATED);
						inventoryLog.setItem_nbr(storeOrd.getItem_nbr());
						inventoryLog.setOrder_line_nbr(storeOrd.getOrder_line_nbr());
						inventoryLog.setQty(storeOrd.getOrdered_qty()); 
						InventoryLogDAO.saveDelivery(inventoryLog);
					}
				} else {
					StoreOrder_ex storeOrder_Ex = new StoreOrder_ex();
					storeOrder_Ex.setStoreOrder(storeOrder);
					storeOrder_Ex.setError_desc(ret);
					ret = storeOrderDownloadDAO.loadstoreOrder_ex(storeOrder_Ex);
				}
			} else {
				StoreOrder_ex storeOrder_Ex = new StoreOrder_ex();
				storeOrder_Ex.setStoreOrder(storeOrder);
				storeOrder_Ex.setError_desc(ret);
				ret = storeOrderDownloadDAO.loadstoreOrder_ex(storeOrder_Ex);
			}

		}
		logger.info("Process returns :" + ret);
		return ret;
	}

	public String validate(StoreOrder strOrder) {
		String ret = Constant.STR_SUCCESS;
		udpateModifier(strOrder);
		ret = StoreOrdDownloadDAO.validateStoreNbr(strOrder);

		if (ret == Constant.STR_SUCCESS) {
			if ((ret = StoreOrdDownloadDAO.validateOrdNumber(strOrder.get_id())) == Constant.STR_SUCCESS) {
				for (StoreOrderLine coline : strOrder.getStoreOrderLinelst()) {

					ret = StoreOrdDownloadDAO.validateItemNbr(coline);
					if (ret != Constant.STR_SUCCESS) {
						return ret;
					}

					ret = StoreOrdDownloadDAO.validateqty(coline);
					if (ret != Constant.STR_SUCCESS) {
						return ret;
					}
				}
			}
			
		}
		return ret;
	}

	public void udpateModifier(StoreOrder storeOrder) {
		Date date = new Date();
		storeOrder.setLast_modified_ts(new Timestamp(date.getTime()));
		storeOrder.setCreate_ts(new Timestamp(date.getTime()));
		storeOrder.setLast_modified_prg(Constant.STOREPRG_NAME);
		storeOrder.setLast_modified_user(Constant.DFTL_USRID);
		storeOrder.setOrder_status(Constant.INIT_PO);
	}

}
