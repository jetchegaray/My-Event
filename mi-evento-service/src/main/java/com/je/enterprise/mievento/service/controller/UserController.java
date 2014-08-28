package com.je.enterprise.mievento.service.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.je.enterprise.mievento.api.dto.User;
import com.je.enterprise.mievento.domain.entity.common.UserEntity;
import com.je.enterprise.mievento.domain.service.impl.UserService;
import com.je.enterprise.mievento.domain.transformer.impl.UserApiToDomainTransformer;

@Controller
@RequestMapping(value= "/user")
public class UserController {
	
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserApiToDomainTransformer UserApiToDomainTransformer;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public String login(@RequestBody User user){
	
		logger.info("new User with mail"+user.getEmail());
		UserEntity userLogin = userService.findByMail(user.getEmail());
		return userLogin.getId().toString();
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public void signUp(@RequestBody User user){
		
		if (!userService.exists(user.getEmail())){

			UserEntity entity = UserApiToDomainTransformer.transform(user);
			userService.create(entity);
			logger.info("new User with mail"+entity.getEmail());
		}else{
			throw new RuntimeException("El usuario existe");
		}
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE)
	public void logout(String token){
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(){
		return "aaa";
	}
	
}
