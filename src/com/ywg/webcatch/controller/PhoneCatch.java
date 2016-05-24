package com.ywg.webcatch.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ywg.webcatch.service.PhoneCatchService;

@Controller
public class PhoneCatch {
	@Resource
	PhoneCatchService phoneCatchService;
	
	@RequestMapping(value = "/phone")
	public ModelAndView phone(@RequestParam("num") String num)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		phoneCatchService.getPhone(num);
		return null;
	}
}
