package com.kuiper.common.servlet;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.common.bo.StoreOrderBo;
import com.kuiper.common.model.StoreOrder_num_list;

@Path("/Misc")
@Consumes("multipart/related")
public class StoreOrderService {
	final static Logger logger = Logger.getLogger(StoreOrderService.class);

	
	@GET
	@Path("getCOList")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public  Response getCOList() {
				
		List<Integer> colist = StoreOrderBo.process();
		StoreOrder_num_list conumlist = new StoreOrder_num_list();
		conumlist.setColst(colist);
		return Response.ok(conumlist, MediaType.APPLICATION_JSON).build();
	}

}
