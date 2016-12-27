package com.kuiper.receive.servlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.receive.bo.UpdatePOBo;
import com.kuiper.receive.constants.Constant;
import com.kuiper.receive.model.DeliveryResponse;
import com.kuiper.receive.model.PurchaseOrder;

@Path("/receiving")
@Consumes("multipart/related")
public class UpdatePOService {

	final static Logger logger = Logger.getLogger(UpdatePOService.class);

	@POST
	@Path("updatePO")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response UpdatePO(PurchaseOrder po) {

		String ret;

		DeliveryResponse deliveryResponse = new DeliveryResponse();
		ret = UpdatePOBo.process(po);

		if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
			logger.info("Update was sucessful");
			deliveryResponse.setStatus(Constant.STR_SUCCESS);
			deliveryResponse.setStatusDesc(Constant.STR_SUCCESS);
			return Response.ok(deliveryResponse, MediaType.APPLICATION_JSON).build();
		} else {
			logger.info("Update failure -" + ret);

			deliveryResponse.setStatus(Constant.STR_FAILURE);
			deliveryResponse.setStatusDesc(ret);
			return Response.ok(deliveryResponse, MediaType.APPLICATION_JSON).build();
		}

	}
}

