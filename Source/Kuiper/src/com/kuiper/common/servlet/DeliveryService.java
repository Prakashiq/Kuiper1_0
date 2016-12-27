package com.kuiper.common.servlet;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.common.bo.DeliveryBo;
import com.kuiper.common.model.Po_num_list;

@Path("/Misc")
@Consumes("multipart/related")
public class DeliveryService {
	final static Logger logger = Logger.getLogger(DeliveryService.class);

	
	@GET
	@Path("getPOList")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public  Response getPOList() {
		
		
		List<Integer> polist = DeliveryBo.process();
		Po_num_list ponumlist = new Po_num_list();
		
		ponumlist.setPolst(polist);
		return Response.ok(ponumlist, MediaType.APPLICATION_JSON).build();
		

	}

}
