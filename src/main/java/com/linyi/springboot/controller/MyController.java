package com.linyi.springboot.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

	@PostMapping("/user/dologin")
	public String getLogin(@RequestParam("userName") String name, @RequestParam("password") String password,
			Map<String, Object> map, HttpSession session) {
		if (!(StringUtils.isEmpty(name) || StringUtils.isEmpty(password))) {
			if ("admin".equals(name) && "123456".equals(password)) {
				// 登陆成功
				session.setAttribute("loginUser", name);
				return "redirect:/main.html";
			}
		}
		// 登陆失败
		map.put("msg", "用户名或者密码错误");
		return "/login";
	}
}
