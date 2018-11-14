package com.web.gmall.service.stub;

import java.util.List;

import com.web.gmall.bean.UserAddress;
import com.web.gmall.service.UserService;


public class UserServiceStub implements UserService {
	
	private final UserService userService;
	

	/**
	 * 传入的是userService远程的代理对象
	 * @param userService
	 */
	public UserServiceStub(UserService userService) {
		super();
		this.userService = userService;
	}


	@Override
	public List<UserAddress> getUserAddressList(String userId) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceStub.....");
		if(userId!=null&&userId!="") {
			return userService.getUserAddressList(userId);
		}
		return null;
	}

}