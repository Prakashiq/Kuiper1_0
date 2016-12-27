package com.kuiper.shipping.servlet;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.kuiper.shipping.bo.GetStrOrdDetailsBO;
import com.kuiper.shipping.model.StoreOrder;

@Path("/shipping")
@Consumes("multipart/related")
public class GetStrOrdDetailsService {
	final static Logger logger = Logger.getLogger(GetStrOrdDetailsService.class);

	@GET
	@Path("getStrOrdList")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<StoreOrder> getCOList() {
		return GetStrOrdDetailsBO.process();
	}
}
