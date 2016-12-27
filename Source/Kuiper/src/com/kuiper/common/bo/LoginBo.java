package com.kuiper.common.bo;

import com.kuiper.common.dao.LoginDao;
import com.kuiper.common.model.User;

public class LoginBo {

	public static int process(User user) {
		
		return LoginDao.validate(user);
	}

}
