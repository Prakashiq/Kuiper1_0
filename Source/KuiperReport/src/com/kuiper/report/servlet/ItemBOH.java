//package com.kuiper.report.servlet;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.PrintStream;
//import java.util.List;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//import org.apache.log4j.Logger;
//
//import com.kuiper.report.bo.ReportFactoryBO;
//import com.kuiper.report.dao.BohDAO;
//import com.kuiper.report.model.BalanceOnHand;
//import com.kuiper.report.model.BalanceOnHandList;
//
//
//@Path("/Report")
//@Consumes("multipart/related")
//public class ItemBOH {
//	final static Logger logger = Logger.getLogger(ItemBOH.class);
//
//	@GET
//	@Path("ItemBOH")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public List<BalanceOnHand> getBOHList() {
//		List<BalanceOnHand>bohlst = BohDAO.getBohList();
//		
//		BalanceOnHandList bohlist = new BalanceOnHandList();
//		bohlist.setStoreOrderlst(bohlist);
//		
//		try {
//			ReportFactoryBO.process("StoreOrderReport",coList.getStoreOrderlst());
//		} catch (FileNotFoundException e) {
//			ByteArrayOutputStream os = new ByteArrayOutputStream();
//			e.printStackTrace(new PrintStream(os));
//			logger.error(new String(os.toByteArray()));
//		}
//		
//		
//		return storelst;
//	}
//}
