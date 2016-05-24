package com.ywg.webcatch.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ywg.webcatch.job.JDqiandaoJob;

@Controller
public class TestC {
	@Resource
	JDqiandaoJob jDqiandaoJob;
	
	@RequestMapping(value = "/test")
	public ModelAndView test()
			throws Exception {
		ModelAndView mav = new ModelAndView();
		jDqiandaoJob.qiandao();
		return null;
	}
}
