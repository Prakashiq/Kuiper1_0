package com.kuiper.common.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.kuiper.common.dao.POSumCntDAO;
import com.kuiper.common.dao.SOSumCntDAO;
import com.kuiper.common.model.SoSummaryCnt;

public class SoSumCntBo {

	final static Logger logger = Logger.getLogger(SoSumCntBo.class);
	static POSumCntDAO poSumCntDAO = null;

	public static List<SoSummaryCnt> process() {
		return SOSumCntDAO.getSOSummaryCnt();
	}

}
