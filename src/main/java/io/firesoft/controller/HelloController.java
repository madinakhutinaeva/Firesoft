package io.firesoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	
	@RequestMapping(value="/oauth2callback", method = RequestMethod.GET)
	public String redirect(){
		return "redirect:resources/authform.html?mode=select";
	}

}
