package com.kuiper.receive.servlet;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.kuiper.receive.bo.GetPODetailsBO;
import com.kuiper.receive.model.PurchaseOrder;

@Path("/receiving")
@Consumes("multipart/related")
public class GetPODetailsService {
	final static Logger logger = Logger.getLogger(GetPODetailsService.class);

	@GET
	@Path("getPOList")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<PurchaseOrder> getPOList() {
		return GetPODetailsBO.process();
	}
}
