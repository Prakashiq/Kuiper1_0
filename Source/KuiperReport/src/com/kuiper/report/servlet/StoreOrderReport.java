package com.kuiper.report.servlet;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.kuiper.report.bo.ReportFactoryBO;
import com.kuiper.report.dao.StoreOrderDetailsDAO;
import com.kuiper.report.model.StoreOrder;
import com.kuiper.report.model.StoreOrderList;


@Path("/Report")
@Consumes("multipart/related")
public class StoreOrderReport {
	final static Logger logger = Logger.getLogger(StoreOrderReport.class);

	@GET
	@Path("StoreOrder")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<StoreOrder> getCOList() {
		List<StoreOrder> storelst = StoreOrderDetailsDAO.getCOList();
		
		StoreOrderList coList = new StoreOrderList();
		coList.setStoreOrderList(storelst);
		
		try {
			ReportFactoryBO.process("StoreOrderReport",coList.getStoreOrderList());
		} catch (FileNotFoundException e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
		}
		
		
		return storelst;
	}
}
