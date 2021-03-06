package com.kuiper.shipping.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.dao.DBInit;
import com.kuiper.shipping.dao.InventoryLogDAO;
import com.kuiper.shipping.dao.InvntLogSeqDAO;
import com.kuiper.shipping.model.InventoryLog;
import com.kuiper.shipping.model.SequenceException;

public class InventoryLogBO {
	final static Logger logger = Logger.getLogger(InventoryLogBO.class);
	static InventoryLogDAO inventoryLogDAO = null;

	public InventoryLogBO() {
		inventoryLogDAO = new InventoryLogDAO();
	}

	public String process(InventoryLog invtlog) {
		String ret = Constant.STR_FAILURE;
		ret = DBInit.initateDB();
		if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
			ret = udpateModifier(invtlog);
			if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
				ret = InventoryLogDAO.saveDelivery(invtlog);
			}
		}

		logger.info("Process returns :" + ret);
		return ret;
	}

	public String udpateModifier(InventoryLog invtLog) {
		Date date = new Date();
		invtLog.setLast_modified_ts(new Timestamp(date.getTime()));
		invtLog.setLast_modified_prg(Constant.REC_PRG_NAME);
		invtLog.setLast_modified_user(Constant.DFTL_USRID);
		try {
			invtLog.set_id(InvntLogSeqDAO.getNextSequenceId("Inv_req_id"));
		} catch (SequenceException e) {
			e.printStackTrace();
			return Constant.SEQ_OBJ_ERR;
		}
		return Constant.STR_SUCCESS;
	}
}
