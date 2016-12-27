package com.kuiper.loadPO.servlet;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.loadPO.bo.PODownloadBO;
import com.kuiper.loadPO.constants.Constant;
import com.kuiper.loadPO.model.POdownloadResponse;
import com.kuiper.loadPO.model.PurchaseOrderList;

@Path("/load")
@Consumes("multipart/related")
public class loadPOService {

	final static Logger logger = Logger.getLogger(loadPOService.class);

	@POST
	@Path("PurchaseOrder")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response LoadItem(PurchaseOrderList polst) {

		POdownloadResponse poloadResponse = new POdownloadResponse();
		PODownloadBO poDownloadBO = new PODownloadBO();
		String ret = Constant.STR_FAILURE;
		try {
			
			if  (polst.getPurchaseOrderList().isEmpty() == false) 
			{
				ret = poDownloadBO.process(polst);
			} 
			else
			{
				ret = Constant.EMPT_LIST;
			}
			

		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));

			poloadResponse.setStatus(Constant.STR_FAILURE);
			poloadResponse.setStatusDesc(ret);
			return Response.ok(poloadResponse, MediaType.APPLICATION_XML).build();

		}
		
		if ( ret.equalsIgnoreCase(Constant.STR_SUCCESS))
		{
			poloadResponse.setStatus(Constant.STR_SUCCESS);
			poloadResponse.setStatusDesc(Constant.PO_STATUSDESC_SUCCESS);
			return Response.ok(poloadResponse, MediaType.APPLICATION_XML).build();
		}
		else
		{
			poloadResponse.setStatus(Constant.STR_FAILURE);
			poloadResponse.setStatusDesc(ret);
			return Response.ok(poloadResponse, MediaType.APPLICATION_XML).build();
		}
		
	}

}
