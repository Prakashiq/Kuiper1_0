package com.kuiper.common.servlet;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kuiper.common.bo.PoSumCntBo;
import com.kuiper.common.model.PoSummaryCnt;

@Path("/Misc")
@Consumes("multipart/related")
public class PurchaseOrderSummaryCnt {

	@GET
	@Path("getPOSumCnt")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<PoSummaryCnt> getpoSummCnt() {

		return PoSumCntBo.process();

	}

}
