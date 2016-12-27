package com.kuiper.shipping.servlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.shipping.bo.UpdateDeliveryBo;
import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.model.TrailerResponse;
import com.kuiper.shipping.model.UpdateTrailer;


@Path("/shipping")
@Consumes("multipart/related")
public class UpdateShippingService {

	final static Logger logger = Logger.getLogger(UpdateShippingService.class);

	@POST
	@Path("updateTrailer")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response UpdateTrailer(UpdateTrailer updatedelivery) {

		String ret;
		
		TrailerResponse deliveryResponse = new TrailerResponse();
		ret = UpdateDeliveryBo.process(updatedelivery);
		
		if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
			logger.info("Update was sucessful");
			deliveryResponse.setStatus(Constant.STR_SUCCESS);
			deliveryResponse.setStatusDesc(Constant.STR_SUCCESS);
			return Response.ok(deliveryResponse, MediaType.APPLICATION_JSON).build();
		} else {
			logger.info("Update failure -" +ret);
			
			deliveryResponse.setStatus(Constant.STR_FAILURE);
			deliveryResponse.setStatusDesc(ret);
			return Response.ok(deliveryResponse, MediaType.APPLICATION_JSON).build();
		}

	}
}
