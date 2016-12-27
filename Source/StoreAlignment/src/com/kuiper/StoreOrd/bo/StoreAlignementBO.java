package com.kuiper.StoreOrd.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.kuiper.StoreOrd.constants.Constant;
import com.kuiper.StoreOrd.dao.StoreAlignmentDAO;
import com.kuiper.StoreOrd.model.Store;
import com.kuiper.StoreOrd.model.StoreList;

public class StoreAlignementBO {
	final static Logger logger = Logger.getLogger(StoreAlignementBO.class);
	//static StoreAlignmentDAO storeAlignmentDAO = null;
	
	public String process(StoreList storelist) {
		String ret = Constant.STR_FAILURE;
		for (Store store : storelist.getStoreList()) {

			ret = validate(store);
			if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
				ret = StoreAlignmentDAO.loadStore(store);
		     }
		}
		logger.info("Process returns :" + ret);
		return ret;
	}

	public String validate(Store store) {
		String ret = Constant.STR_SUCCESS;
		udpateModifier(store);

		
				ret = StoreAlignmentDAO.validateStoreNbr(store);
				if (ret != Constant.STR_SUCCESS) {
					return ret;
				}
		return ret;
	}

	public void udpateModifier(Store store) {
		Date date = new Date();
		store.setLast_modified_ts(new Timestamp(date.getTime()));
		store.setLast_modified_prg(Constant.STORALGEPRG_NAME);
		store.setLast_modified_user(Constant.DFTL_USRID);
	}

}
