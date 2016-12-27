package com.kuiper.common.servlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.kuiper.common.bo.LoginBo;
import com.kuiper.common.constants.Constant;
import com.kuiper.common.model.LoginResponse;
import com.kuiper.common.model.User;

@Path("/Auth")
@Consumes("multipart/related")
public class LoginService {

	final static Logger logger = Logger.getLogger(LoginService.class);

	@POST
	@Path("login")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUser(User user) {

		LoginResponse loginResponse = new LoginResponse();

		if (LoginBo.process(user) == Constant.SUCCESS) {
			logger.info("login sucessful");
			loginResponse.setStatus(Constant.STR_SUCCESS);
			loginResponse.setStatusDesc(Constant.LOGIN_STATUSDESC_SUCCESS);
			return Response.ok(loginResponse, MediaType.APPLICATION_JSON).build();
		} else {
			logger.info("login failure");
			loginResponse.setStatus(Constant.STR_FAILURE);
			loginResponse.setStatusDesc(Constant.LOGIN_STATUSDESC_ERROR);
			return Response.ok(loginResponse, MediaType.APPLICATION_JSON).build();
		}

	}

}
