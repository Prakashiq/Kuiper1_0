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

import com.kuiper.shipping.bo.LoadBuilderBO;
import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.model.Trailer;
import com.kuiper.shipping.model.TrailerResponse;

@Path("/shipping")
@Consumes("multipart/related")
public class CreateShippingService {

	final static Logger logger = Logger.getLogger(CreateShippingService.class);

	@POST
	@Path("buildLoad")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buildLoad(Trailer trailer) {

		TrailerResponse trailerResponse = new TrailerResponse();
		LoadBuilderBO trailerBO = new LoadBuilderBO();
		String ret ;

		try {
			ret= trailerBO.process(trailer);
			if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
				trailerResponse.setStatus(Constant.STR_SUCCESS);
				trailerResponse.setStatusDesc(Constant.TRL_STATUSDESC_SUCCESS);
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
