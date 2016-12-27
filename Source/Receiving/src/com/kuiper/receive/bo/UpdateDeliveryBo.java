package com.kuiper.receive.bo;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kuiper.receive.constants.Constant;
import com.kuiper.receive.dao.UpdateDeliveryDao;
import com.kuiper.receive.model.UpdateDelivery;


public class UpdateDeliveryBo {
	
	final static Logger logger = Logger.getLogger(UpdateDeliveryBo.class);

	public static String process(UpdateDelivery updatedelivery) {
		String ret = Constant.STR_SUCCESS;
		if ((updatedelivery.getDelivery_num() > 0) && (updatedelivery.getPo_status() != null)) {
			 ret = UpdateDeliveryDao.update(updatedelivery);
			 if (ret.equalsIgnoreCase(Constant.STR_SUCCESS))
			 {
				 GsonBuilder builder = new GsonBuilder();
			     Gson gson = builder.create();
			     String jsonmsg = gson.toJson(updatedelivery);
			        logger.info("BroadCastMessage :"+ jsonmsg);
				 new BroadCastMessage().sendmessage(jsonmsg);
			 }
			 return ret;
		} else {
			return Constant.DATA_FORMAT_ERR;
		}
	}

}
