package com.kuiper.receive.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kuiper.receive.constants.Constant;
import com.kuiper.receive.dao.UpdatePODao;
import com.kuiper.receive.model.PurchaseOrder;
import com.kuiper.receive.model.PurchaseOrderLine;


public class UpdatePOBo {
	final static Logger logger = Logger.getLogger(UpdatePOBo.class);


	public static String process(PurchaseOrder po) {
		String ret;
		if (po != null && (po.getPurchaseOrderLinelst() != null)) {
			ret = validatePO_qty(po);
			if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
				po.setPo_status(validatePO_Status(po));
				ret= UpdatePODao.update(po);
				if (ret.equalsIgnoreCase(Constant.STR_SUCCESS))
				{
					 GsonBuilder builder = new GsonBuilder();
				     Gson gson = builder.create();
				     String jsonmsg = gson.toJson(po);
				        logger.info("BroadCastMessage :"+ jsonmsg);
					 new BroadCastMessage().sendmessage(jsonmsg);
				}
			} 
				return ret;
			
		} else {
			return Constant.DATA_FORMAT_ERR;
		}
	}

	public static String validatePO_Status(PurchaseOrder po) {

		udpateModifier(po);
		for (PurchaseOrderLine poline : po.getPurchaseOrderLinelst()) {

			if (poline.getOrdered_qty() != poline.getReceived_qty()) {
				return Constant.PO_RIP;
			}
			if ((poline.getDue_qty() + poline.getReceived_qty()) > poline.getOrdered_qty()) {
				return Constant.CNT_RCV_OVERAGE;
			}
		}
		return Constant.RECIVED_PO;
	}

	public static String validatePO_qty(PurchaseOrder po) {
		for (PurchaseOrderLine poline : po.getPurchaseOrderLinelst()) {

			if ((poline.getDue_qty() + poline.getReceived_qty()) > poline.getOrdered_qty()) {
				return Constant.CNT_RCV_OVERAGE;
			}
		}

		return Constant.STR_SUCCESS;
	}

	public static void udpateModifier(PurchaseOrder po) {
		Date date = new Date();
		po.setLast_modified_ts(new Timestamp(date.getTime()));
		po.setCreate_ts(new Timestamp(date.getTime()));
		po.setLast_modified_prg(Constant.REC_PRG_NAME);
		po.setLast_modified_user(Constant.DFTL_USRID);

	}
}
