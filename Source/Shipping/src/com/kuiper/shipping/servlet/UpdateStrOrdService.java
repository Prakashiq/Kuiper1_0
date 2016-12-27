package com.kuiper.shipping.servlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.shipping.bo.UpdateStrOrdBo;
import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.model.StoreOrder;
import com.kuiper.shipping.model.TrailerResponse;

@Path("/shipping")
@Consumes("multipart/related")
public class UpdateStrOrdService {

	final static Logger logger = Logger.getLogger(UpdateStrOrdService.class);

	@POST
	@Path("updateSO")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response UpdateCO(StoreOrder strOrd) {

		String ret;

		TrailerResponse trailerResponse = new TrailerResponse();
		ret = UpdateStrOrdBo.process(strOrd);

		if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
			logger.info("Update was sucessful");
			trailerResponse.setStatus(Constant.STR_SUCCESS);
			trailerResponse.setStatusDesc(Constant.STR_SUCCESS);
			return Response.ok(trailerResponse, MediaType.APPLICATION_JSON).build();
		} else {
			logger.info("Update failure -" + ret);

			trailerResponse.setStatus(Constant.STR_FAILURE);
			trailerResponse.setStatusDesc(ret);
			return Response.ok(trailerResponse, MediaType.APPLICATION_JSON).build();
		}

	}
}

