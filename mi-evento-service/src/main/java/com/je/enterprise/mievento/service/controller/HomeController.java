package com.je.enterprise.mievento.service.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);
	 
	@RequestMapping(value= "/home",method = RequestMethod.GET)
	public ModelAndView welcome(){
		return new ModelAndView("index");
	}
}
