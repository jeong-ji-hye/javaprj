package com.newlecture.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@GetMapping("login")
	public String login() {
		return "member.login";
	}
	/* 스프링이 만든걸 쓸거니까 post메소드 안함 */
}
