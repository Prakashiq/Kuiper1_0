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

import com.kuiper.StoreOrd.bo.StoreAlignementBO;
import com.kuiper.StoreOrd.constants.Constant;
import com.kuiper.StoreOrd.model.StoreAlignmentResponse;
import com.kuiper.StoreOrd.model.StoreList;

@Path("/load")
@Consumes("multipart/related")
public class storeAlignmentService {

	final static Logger logger = Logger.getLogger(storeAlignmentService.class);

	@POST
	@Path("Store")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response LoadItem(StoreList storeList) {

		StoreAlignmentResponse storeResponse = new StoreAlignmentResponse();
		StoreAlignementBO coDownloadBO = new StoreAlignementBO();
		String ret=Constant.EMPT_LIST;
		try {
			if (storeList.getStoreList().isEmpty() == false) {
				ret = coDownloadBO.process(storeList);
				if (ret.equalsIgnoreCase(Constant.STR_SUCCESS)) {
					storeResponse.setStatus(ret);
					storeResponse.setStatusDesc(Constant.CO_STATUSDESC_SUCCESS);
					return Response.ok(storeResponse, MediaType.APPLICATION_XML).build();
				}
			}
			storeResponse.setStatus(Constant.STR_FAILURE);
			storeResponse.setStatusDesc(ret);
			return Response.ok(storeResponse, MediaType.APPLICATION_XML).build();

		} catch (

		Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));

			storeResponse.setStatus(Constant.STR_FAILURE);
			storeResponse.setStatusDesc(Constant.EXECEPTION);
			return Response.ok(storeResponse, MediaType.APPLICATION_XML).build();

		}

	}

}
