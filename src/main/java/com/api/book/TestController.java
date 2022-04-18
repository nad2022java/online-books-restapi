package com.api.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		
		int a = 20;
		int b = 40;
		return "this is testing \t sum of a and b is "+(a+b);
	}

}
