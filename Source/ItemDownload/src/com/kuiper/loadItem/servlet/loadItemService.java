package com.kuiper.loadItem.servlet;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.loadItem.bo.ItemDownloadBO;
import com.kuiper.loadItem.constants.Constant;
import com.kuiper.loadItem.model.ItemList;
import com.kuiper.loadItem.model.ItemdownloadResponse;

@Path("/load")
@Consumes("multipart/related")
public class loadItemService {

	final static Logger logger = Logger.getLogger(loadItemService.class);

	@POST
	@Path("Item")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response LoadItem(ItemList itemList) {

		ItemdownloadResponse itemdownloadResponse = new ItemdownloadResponse();
		ItemDownloadBO itemDownloadBO = new ItemDownloadBO();

		try {

			if ((itemList.getItemlst().isEmpty() == false) && (itemDownloadBO.process(itemList) == Constant.SUCCESS)) {
				itemdownloadResponse.setStatus(Constant.STR_SUCCESS);
				itemdownloadResponse.setStatusDesc(Constant.ITMLD_STATUSDESC_SUCCESS);
				return Response.ok(itemdownloadResponse, MediaType.APPLICATION_XML).build();
			} else {
				itemdownloadResponse.setStatus(Constant.STR_FAILURE);
				itemdownloadResponse.setStatusDesc(Constant.ITMLD_STATUSDESC_ERROR);
				return Response.ok(itemdownloadResponse, MediaType.APPLICATION_XML).build();
			}

		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));

			itemdownloadResponse.setStatus(Constant.STR_FAILURE);
			itemdownloadResponse.setStatusDesc(Constant.ITMLD_STATUSDESC_ERROR);
			return Response.ok(itemdownloadResponse, MediaType.APPLICATION_XML).build();

		}

	}

}
