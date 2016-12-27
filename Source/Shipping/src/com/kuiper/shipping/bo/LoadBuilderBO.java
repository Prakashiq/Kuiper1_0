package com.kuiper.shipping.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.dao.DBInit;
import com.kuiper.shipping.dao.LoadBuilderDAO;
import com.kuiper.shipping.model.Carrier;
import com.kuiper.shipping.model.StoreOrder;
import com.kuiper.shipping.model.Trailer;

public class LoadBuilderBO {
	final static Logger logger = Logger.getLogger(LoadBuilderBO.class);
	static LoadBuilderDAO deliveryDAO = null;

	public LoadBuilderBO() {
		deliveryDAO = new LoadBuilderDAO();
	}

	public String process(Trailer trailer) {
		String ret = Constant.STR_FAILURE;
		int lock = 0;
		ret = DBInit.initateDB();
		if (ret == Constant.STR_SUCCESS) {
			List<StoreOrder> storeOrderlst = deliveryDAO.loadStrOrdList(trailer.getStoreOrderNum());

			for (StoreOrder storeOrder : storeOrderlst) {
				udpateModifier(storeOrder);
				storeOrder.setTrailer(trailer);
				storeOrder.setOrder_status(Constant.ASSIGNED_TRAILER);

				Carrier carrier = deliveryDAO.getCarrierid(storeOrder.getTrailer().getCarrier_id());
				storeOrder.setCarrier(carrier);

				ret = deliveryDAO.saveStoreOrder(storeOrder);
				if (ret.equalsIgnoreCase(Constant.STR_SUCCESS))
				{
					 GsonBuilder builder = new GsonBuilder();
				     Gson gson = builder.create();
				     String jsonmsg = gson.toJson(storeOrder);
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
		if (lock == 1) {
			ret = Constant.STR_FAILURE;
		}
		logger.info("Process returns :" + ret);
		return ret;
	}


	public void udpateModifier(StoreOrder co) {
		Date date = new Date();
		co.setLast_modified_ts(new Timestamp(date.getTime()));
		co.setCreate_ts(new Timestamp(date.getTime()));
		co.setLast_modified_prg(Constant.DELIVERY_PRG_NAME);
		co.setLast_modified_user(Constant.DFTL_USRID);
		co.setOrder_status(Constant.INIT_CO);

	}
}
