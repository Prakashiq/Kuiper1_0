package com.kuiper.receive.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kuiper.receive.constants.Constant;
import com.kuiper.receive.dao.DBInit;
import com.kuiper.receive.dao.DeliveryDAO;
import com.kuiper.receive.model.Delivery;
import com.kuiper.receive.model.PurchaseOrder;

public class DeliveryBO {
	final static Logger logger = Logger.getLogger(DeliveryBO.class);
	static DeliveryDAO deliveryDAO = null;

	public DeliveryBO()
	{
		deliveryDAO = new DeliveryDAO();
	}
	
	public String process(Delivery delivery) {
		String ret = Constant.STR_FAILURE;
		int lock = 0;
		ret = DBInit.initateDB();
		if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
			ret = validate(delivery);
			if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
				List<PurchaseOrder> polst = deliveryDAO.loadPOList(delivery.getPo_num());

				for (PurchaseOrder po : polst) {
					udpateModifier(po);
					po.setDelivery(delivery);
					po.setPo_status(Constant.ASSIGNED_DELIVERY_PO);
					ret = deliveryDAO.saveDelivery(po);
					
					if (ret.equalsIgnoreCase(Constant.STR_SUCCESS))
					{
						 GsonBuilder builder = new GsonBuilder();
					     Gson gson = builder.create();
					     String jsonmsg = gson.toJson(po);
					        logger.info("BroadCastMessage :"+ jsonmsg);
						 new BroadCastMessage().sendmessage(jsonmsg);
					}
					
					if (ret != Constant.STR_SUCCESS) {
						if (lock == 0) {
							lock = 1;
						}
					}
				}
			}
		}
		if (lock == 1) {
			ret = Constant.STR_FAILURE;
		}
		logger.info("Process returns :" + ret);
		return ret;
	}

	public String validate(Delivery delivery) {
		return deliveryDAO.isDeliveryExist(delivery.getDelivery_num());

	}

	public void udpateModifier(PurchaseOrder po) {
		Date date = new Date();
		po.setLast_modified_ts(new Timestamp(date.getTime()));
		po.setCreate_ts(new Timestamp(date.getTime()));
		po.setLast_modified_prg(Constant.DELIVERY_PRG_NAME);
		po.setLast_modified_user(Constant.DFTL_USRID);
		po.setPo_status(Constant.INIT_PO);

	}
}
