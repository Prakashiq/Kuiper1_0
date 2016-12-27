package com.kuiper.shipping.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.dao.InventoryLogDAO;
import com.kuiper.shipping.dao.InvntLogSeqDAO;
import com.kuiper.shipping.dao.UpdateStrOrdDao;
import com.kuiper.shipping.model.InventoryLog;
import com.kuiper.shipping.model.SequenceException;
import com.kuiper.shipping.model.StoreOrder;
import com.kuiper.shipping.model.StoreOrderLine;


public class UpdateStrOrdBo {

	final static Logger logger = Logger.getLogger(LoadBuilderBO.class);
	
	public static String process(StoreOrder co) {
		String ret=Constant.STR_SUCCESS; 
		if (co!= null && (co.getStoreOrderLinelst()!= null)) {
			co.setOrder_status(validateCO_Status(co));
			ret = UpdateStrOrdDao.update(co);

			InventoryLog inventoryLog = new InventoryLog();

			try {
				inventoryLog.set_id(InvntLogSeqDAO.getNextSequenceId("Inv_req_id"));
			} catch (SequenceException e) {
				e.printStackTrace();
				return Constant.SEQ_OBJ_ERR;
			}
			inventoryLog.setLast_modified_prg(co.getLast_modified_prg());
			inventoryLog.setLast_modified_ts(co.getLast_modified_ts());
			inventoryLog.setLast_modified_user(co.getLast_modified_user());
			inventoryLog.setOrder_nbr(co.get_id());
			
			for (StoreOrderLine storeOrd : co.getStoreOrderLinelst()) {

				inventoryLog.setAction(co.getOrder_status());
				inventoryLog.setItem_nbr(storeOrd.getItem_nbr());
				inventoryLog.setOrder_line_nbr(storeOrd.getOrder_line_nbr());	
				inventoryLog.setQty(storeOrd.getOrdered_qty()); 
				InventoryLogDAO.saveDelivery(inventoryLog);
			}
			
			
			if (ret.equalsIgnoreCase(Constant.STR_SUCCESS))
			{
				 GsonBuilder builder = new GsonBuilder();
			     Gson gson = builder.create();
			     String jsonmsg = gson.toJson(co);
			        logger.info("BroadCastMessage :"+ jsonmsg);
				 new BroadCastMessage().sendmessage(jsonmsg);
			}
		} else {
			return Constant.DATA_FORMAT_ERR;
		}
		return ret;
	}

	public static String validateCO_Status(StoreOrder co) {

		udpateModifier(co);
		for (StoreOrderLine coline : co.getStoreOrderLinelst()) {

			if (coline.getOrdered_qty() != coline.getFulfilled_qty()) {
				return Constant.LIP;
			}
		}
		return Constant.SHIPPED;
	}

	public static void udpateModifier(StoreOrder co) {
		Date date = new Date();
		co.setLast_modified_ts(new Timestamp(date.getTime()));
		co.setCreate_ts(new Timestamp(date.getTime()));
		co.setLast_modified_prg(Constant.REC_PRG_NAME);
		co.setLast_modified_user(Constant.DFTL_USRID);

	}
}
