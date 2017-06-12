package com.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.service.TagService;

@Controller
public class TagController {
	
	@Autowired
	TagService tagService;
	
	@RequestMapping("alltag")
	ModelAndView allTag(HttpSession httpSession)
	{
		httpSession.setAttribute("tags", tagService.findService());
		return new ModelAndView("index");
	}
}
