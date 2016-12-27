package com.kuiper.common.servlet;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.common.bo.CarrierBO;
import com.kuiper.common.constants.Constant;
import com.kuiper.common.model.Carrier;
import com.kuiper.common.model.CarrierResponse;

@Path("/carrier")
@Consumes("multipart/related")
public class CarrierService {

	final static Logger logger = Logger.getLogger(CarrierService.class);

	@POST
	@Path("load")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response LoadDelivery(Carrier carrier) {

		CarrierResponse carrierResponse = new CarrierResponse();
		CarrierBO carrierBO = new CarrierBO();
		String ret ;

		try {
			ret= carrierBO.process(carrier);
			if ( ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
				carrierResponse.setStatus(Constant.STR_SUCCESS);
				carrierResponse.setStatusDesc(Constant.CARR_STATUSDESC_SUCCESS);
				return Response.ok(carrierResponse, MediaType.APPLICATION_JSON).build();
			} else {
				carrierResponse.setStatus(Constant.STR_FAILURE);
				carrierResponse.setStatusDesc(ret);
				return Response.ok(carrierResponse, MediaType.APPLICATION_JSON).build();
			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));

			carrierResponse.setStatus(Constant.STR_FAILURE);
			carrierResponse.setStatusDesc(Constant.EXECEPTION);
			return Response.ok(carrierResponse, MediaType.APPLICATION_JSON).build();

		}
	}

	
	@GET
	@Path("getCarrierList")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Carrier> getCarrierList() {
		return CarrierBO.processCarrierList();
	}
	
	@POST
	@Path("delete")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response DeleteDelivery(Carrier carrier) {

		CarrierResponse carrierResponse = new CarrierResponse();
		CarrierBO carrierBO = new CarrierBO();
		String ret ;

		try {
			ret= carrierBO.processDeleteCarrier(carrier);
			if ( ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
				carrierResponse.setStatus(Constant.STR_SUCCESS);
				carrierResponse.setStatusDesc(Constant.CARR_STATUSDESC_SUCCESS);
				return Response.ok(carrierResponse, MediaType.APPLICATION_JSON).build();
			} else {
				carrierResponse.setStatus(Constant.STR_FAILURE);
				carrierResponse.setStatusDesc(ret);
				return Response.ok(carrierResponse, MediaType.APPLICATION_JSON).build();
			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));

			carrierResponse.setStatus(Constant.STR_FAILURE);
			carrierResponse.setStatusDesc(Constant.EXECEPTION);
			return Response.ok(carrierResponse, MediaType.APPLICATION_JSON).build();

		}
	}

}
