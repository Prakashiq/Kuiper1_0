package com.kuiper.StoreOrd.servlet;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.StoreOrd.bo.StoreOrdDownloadBO;
import com.kuiper.StoreOrd.constants.Constant;
import com.kuiper.StoreOrd.model.StoreOrderList;
import com.kuiper.StoreOrd.model.StoreOrderdownloadResponse;


@Path("/load")
@Consumes("multipart/related")
public class StoreOrderService {

	final static Logger logger = Logger.getLogger(StoreOrderService.class);

	@POST
	@Path("StoreOrder")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response LoadItem(StoreOrderList storeOrderLst) {

		StoreOrderdownloadResponse coloadResponse = new StoreOrderdownloadResponse();
		StoreOrdDownloadBO coDownloadBO = new StoreOrdDownloadBO();
		String ret=Constant.EMPT_LIST;
		try {
			if (storeOrderLst.getStoreOrderList().isEmpty() == false) {
				ret = coDownloadBO.process(storeOrderLst);
				if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
					coloadResponse.setStatus(ret);
					coloadResponse.setStatusDesc(Constant.CO_STATUSDESC_SUCCESS);
					return Response.ok(coloadResponse, MediaType.APPLICATION_XML).build();
				}
			}
			coloadResponse.setStatus(Constant.STR_FAILURE);
			coloadResponse.setStatusDesc(ret);
			return Response.ok(coloadResponse, MediaType.APPLICATION_XML).build();

		} catch (

		Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));

			coloadResponse.setStatus(Constant.STR_FAILURE);
			coloadResponse.setStatusDesc(Constant.EXECEPTION);
			return Response.ok(coloadResponse, MediaType.APPLICATION_XML).build();

		}

	}

}
