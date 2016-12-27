package com.kuiper.common.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.kuiper.common.dao.POSumCntDAO;
import com.kuiper.common.model.PoSummaryCnt;

public class PoSumCntBo {

	final static Logger logger = Logger.getLogger(PoSumCntBo.class);
	static POSumCntDAO poSumCntDAO = null;

	public static List<PoSummaryCnt> process() {
		POSumCntDAO.initateDB();
		return POSumCntDAO.getPOSummaryCnt();
	}

}
