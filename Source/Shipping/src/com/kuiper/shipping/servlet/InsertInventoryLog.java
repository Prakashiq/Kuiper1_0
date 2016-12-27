package com.kuiper.shipping.servlet;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.shipping.bo.InventoryLogBO;
import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.model.InventoryLog;
import com.kuiper.shipping.model.TrailerResponse;


@Path("/shipping")
@Consumes("multipart/related")
public class InsertInventoryLog {

	final static Logger logger = Logger.getLogger(InsertInventoryLog.class);

	@POST
	@Path("inventoryLog")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createInventoryLog(InventoryLog invtlog) {

		TrailerResponse trailerResponse = new TrailerResponse();
		InventoryLogBO invtLogBO = new InventoryLogBO();
		String ret ;

		try {
			ret= invtLogBO.process(invtlog);
			if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
				trailerResponse.setStatus(Constant.STR_SUCCESS);
				trailerResponse.setStatusDesc(Constant.INVLOG_STATUSDESC_SUCCESS);
				return Response.ok(trailerResponse, MediaType.APPLICATION_JSON).build();
			} else {
				trailerResponse.setStatus(Constant.STR_FAILURE);
				trailerResponse.setStatusDesc(ret);
				return Response.ok(trailerResponse, MediaType.APPLICATION_JSON).build();
			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));

			trailerResponse.setStatus(Constant.STR_FAILURE);
			trailerResponse.setStatusDesc(Constant.EXECEPTION);
			return Response.ok(trailerResponse, MediaType.APPLICATION_JSON).build();
		}
	}

}
