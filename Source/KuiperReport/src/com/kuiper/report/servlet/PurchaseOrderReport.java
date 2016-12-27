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
import com.kuiper.report.constants.Constant;
import com.kuiper.report.dao.GetPODetailsDAO;
import com.kuiper.report.model.PurchaseOrder;
import com.kuiper.report.model.PurchaseOrderLine;
import com.kuiper.report.model.PurchaseOrderList;


@Path("/Report")
@Consumes("multipart/related")
public class PurchaseOrderReport {
	final static Logger logger = Logger.getLogger(PurchaseOrderReport.class);

	@GET
	@Path("PurchaseOrder")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<PurchaseOrder> getPOList() {
		List<PurchaseOrder> polst = GetPODetailsDAO.getPOList();

		for (PurchaseOrder po : polst) {
			for (PurchaseOrderLine poline : po.getPurchaseOrderLines()) {
				if (poline.getDue_qty() == 0 && po.getPo_status() != Constant.RECIVED_PO) {
					poline.setDue_qty(poline.getOrdered_qty());
				}
			}
		}
		
		PurchaseOrderList poList = new PurchaseOrderList();
		poList.setPurchaseOrderlst(polst);
		
		try {
			ReportFactoryBO.process("PurchaseOrderReport",poList.getPurchaseOrderlst());
		} catch (FileNotFoundException e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
		}
		
		
		return polst;
	}
}
