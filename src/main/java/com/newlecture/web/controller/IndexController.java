package com.newlecture.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
public class IndexController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mv = new ModelAndView("/WEB-INF/view/index.jsp");
		mv.addObject("x", 30);
		mv.addObject("y", 40);
		mv.addObject("name", "비둘기 저리가");
		mv.addObject("where", "index page");
		return mv;
	}
	
}
