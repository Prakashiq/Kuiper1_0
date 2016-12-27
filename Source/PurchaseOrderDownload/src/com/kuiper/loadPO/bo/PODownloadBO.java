package com.kuiper.loadPO.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kuiper.loadPO.constants.Constant;
import com.kuiper.loadPO.dao.DBInit;
import com.kuiper.loadPO.dao.PODownloadDAO;
import com.kuiper.loadPO.model.PurchaseOrder;
import com.kuiper.loadPO.model.PurchaseOrderLine;
import com.kuiper.loadPO.model.PurchaseOrderList;

public class PODownloadBO {
	final static Logger logger = Logger.getLogger(PODownloadBO.class);
	static PODownloadDAO poDownloadDAO = null;

	public String process(PurchaseOrderList polst) {
		String ret = Constant.STR_FAILURE;
		DBInit.initateDB();

		for (PurchaseOrder po : polst.getPurchaseOrderList()) {
			ret = validate(po) ;
			if (ret.equalsIgnoreCase(Constant.STR_SUCCESS ) ) {
				poDownloadDAO = new PODownloadDAO();
				ret = poDownloadDAO.loadPO(po);
				
				if (ret.equalsIgnoreCase(Constant.STR_SUCCESS))
				{
					 GsonBuilder builder = new GsonBuilder();
				     Gson gson = builder.create();
				     String jsonmsg = gson.toJson(po);
				        logger.info("BroadCastMessage :"+ jsonmsg);
					 new BroadCastMessage().sendmessage(jsonmsg);
				}
			}
		}
		logger.info("Process returns :" + ret);
		return ret;
	}

	public String validate(PurchaseOrder po) {
		
		String ret =Constant.STR_SUCCESS;
		udpateModifier(po);
		
		if (po.getStore_number() != null)
		{
			ret = PODownloadDAO.validateStoreNbr(po.getStore_number());
		}
		
		if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
			for (PurchaseOrderLine poline : po.getPurchaseOrderLinelst()) {
					ret = PODownloadDAO.validateItemNbr(poline);
				if (ret != Constant.STR_SUCCESS) {
					return Constant.ITEM_NOT_FOUND;
				}
			}
		}

		return ret; 
	}

	public void udpateModifier(PurchaseOrder po) {
		Date date = new Date();
		po.setLast_modified_ts(new Timestamp(date.getTime()));
		po.setCreate_ts(new Timestamp(date.getTime()));
		po.setLast_modified_prg(Constant.PO_PRG_NAME);
		po.setLast_modified_user(Constant.DFTL_USRID);
		po.setPo_status(Constant.INIT_PO);
	}
}
