package com.kuiper.shipping.bo;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.dao.UpdateDeliveryDao;
import com.kuiper.shipping.model.UpdateTrailer;

public class UpdateDeliveryBo {

	final static Logger logger = Logger.getLogger(UpdateDeliveryBo.class);
	
	public static String process(UpdateTrailer updatedelivery) {
		String ret = Constant.STR_SUCCESS;
		if ((updatedelivery.getOrder_num() > 0) && (updatedelivery.getOrder_status() != null)) {
			ret= UpdateDeliveryDao.update(updatedelivery);
			
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
