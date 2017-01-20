package com.webmail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webmail.domain.UserInfo;
import com.webmail.domain.Users;
import com.webmail.service.UserInfoService;
import com.webmail.service.UsersService;
import com.webmail.util.EncryptUtil;
import com.webmail.util.JSONUtil;
import com.webmail.util.StatusCode;

@RestController
@RequestMapping(value="/user",produces="application/json;charset=utf-8")
public class UserController {

	@Autowired
	private UsersService usersService;
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(
			@RequestParam(required=true)String username,
			@RequestParam(required=true)String password){
		Users u = usersService.checkNameAndPwd(username, EncryptUtil.encryptSHA(password));
		if(u == null){
			return JSONUtil.writeMessage(StatusCode.NOT_MATCHING, "信息不匹配");
		}
		return JSONUtil.writeMessage(StatusCode.OK, "成功登录");
	}

	/**
	 * 添加用户
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String addUser(
			@RequestParam(required=true)String username,
			@RequestParam(required=true)String password){
		Users u = usersService.query(username);
		if(u != null){
			return JSONUtil.writeMessage(StatusCode.ECHO, "帐号已存在");
		}
		Users user = new Users();
		user.setUsername(username);
		user.setPwdHash(EncryptUtil.encryptSHA(password));
		usersService.add(user);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(username);
		userInfo.setPassword(password);
		userInfoService.add(userInfo);
		return JSONUtil.writeMessage(StatusCode.OK, "添加成功");
	}
}
