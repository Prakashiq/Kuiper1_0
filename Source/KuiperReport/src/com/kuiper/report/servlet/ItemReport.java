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
import com.kuiper.report.dao.ItemDownloadDAO;
import com.kuiper.report.model.Item;
import com.kuiper.report.model.ItemList;

@Path("/Report")
@Consumes("multipart/related")
public class ItemReport {

	final static Logger logger = Logger.getLogger(ItemReport.class);

	@GET
	@Path("Item")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Item> getPOList() {
		
		ItemList itemlst = new ItemList(); 
		List<Item> itmlst= ItemDownloadDAO.getItemList(); 
		try {
			
			itemlst.setItemlst(itmlst);
			ReportFactoryBO.process("ItemReport",itemlst.getItemlst());
		} catch (FileNotFoundException e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
		}
		return itmlst;
	}

}
