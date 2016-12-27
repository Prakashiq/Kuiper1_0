package com.kuiper.common.servlet;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kuiper.common.bo.SoSumCntBo;
import com.kuiper.common.model.SoSummaryCnt;

@Path("/Misc")
@Consumes("multipart/related")
public class StoreOrderSummaryCnt {

	@GET
	@Path("getSOSumCnt")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<SoSummaryCnt> getsoSummCnt() {

		return SoSumCntBo.process();

	}

}
