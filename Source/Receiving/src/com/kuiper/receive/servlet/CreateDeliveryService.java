package com.kuiper.receive.servlet;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.receive.bo.DeliveryBO;
import com.kuiper.receive.constants.Constant;
import com.kuiper.receive.model.DeliveryResponse;
import com.kuiper.receive.model.Delivery;

@Path("/load")
@Consumes("multipart/related")
public class CreateDeliveryService {

	final static Logger logger = Logger.getLogger(CreateDeliveryService.class);

	@POST
	@Path("loadDelivery")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response LoadDelivery(Delivery delivery) {

		DeliveryResponse deliveryResponse = new DeliveryResponse();
		DeliveryBO deliveryBO = new DeliveryBO();
		String ret ;

		try {
			ret= deliveryBO.process(delivery);
			if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
				deliveryResponse.setStatus(Constant.STR_SUCCESS);
				deliveryResponse.setStatusDesc(Constant.DEL_STATUSDESC_SUCCESS);
				return Response.ok(deliveryResponse, MediaType.APPLICATION_JSON).build();
			} else {
				deliveryResponse.setStatus(Constant.STR_FAILURE);
				deliveryResponse.setStatusDesc(ret);
				return Response.ok(deliveryResponse, MediaType.APPLICATION_JSON).build();
			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));

			deliveryResponse.setStatus(Constant.STR_FAILURE);
			deliveryResponse.setStatusDesc(Constant.EXECEPTION);
			return Response.ok(deliveryResponse, MediaType.APPLICATION_JSON).build();

		}
	}

}
