package com.kuiper.common.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.kuiper.common.constants.Constant;
import com.kuiper.common.dao.DBInit;
import com.kuiper.common.dao.carrierDAO;
import com.kuiper.common.model.Carrier;

public class CarrierBO {
	final static Logger logger = Logger.getLogger(CarrierBO.class);

	public String process(Carrier carrier) {
		String ret = Constant.STR_FAILURE;
		ret = DBInit.initateDB();
		if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
		//	ret = validate_carrier(carrier);
			if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
				ret = carrierDAO.saveCarrier(carrier);
			}
		}

		logger.info("Process returns :" + ret);
		return ret;
	}

//	public String validate_carrier(Carrier carrier) {
//		return carrierDAO.isDeliveryExist(carrier.get_id());
//	}

	public void udpateModifier(Carrier carrier) {
		Date date = new Date();
		carrier.setLast_modified_ts(new Timestamp(date.getTime()));
		carrier.setCreate_ts(new Timestamp(date.getTime()));
		carrier.setLast_modified_prg(Constant.CARRPRG_NAME);
		carrier.setLast_modified_user(Constant.DFTL_USRID);
	}

	public static List<Carrier> processCarrierList() {
		return carrierDAO.getCarrierList();
	}

	public String processDeleteCarrier(Carrier carrier) {
		String ret = Constant.STR_FAILURE;
		ret = DBInit.initateDB();
		if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
			if (isCarrierAlreadyInUse(carrier.get_id()) == false) {
				ret = carrierDAO.deleteCarrier(carrier);
			}
			else
			{
				ret = "Carrier is mapped to an Order";
			}
		}

		logger.info("Process returns :" + ret);
		return ret;
	}

	private boolean isCarrierAlreadyInUse(String carrier_id) {
		return carrierDAO.isExistStoreOrder(carrier_id);
				
	}
}
