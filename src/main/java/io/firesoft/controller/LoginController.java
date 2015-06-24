package io.firesoft.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class LoginController {
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String mainPage() {
		return "user";
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String adminPage() {
		return "admin";
	}
	
	/*@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(ModelMap model){
		System.out.println("In the login method");
		return "login";
	}*/

	//Spring Security see this :
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView handleRoot(
        @RequestParam(value = "error", required = false) String error){
        
		System.out.println("login failed");
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
            model.addObject("error", "Invalid username and password!");
        } else {
            model.addObject("error", "No error");
        }
		model.addObject("error", "No error");
		model.addObject("vasya", "No error");
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value="loggedout", method=RequestMethod.GET)
	public String logout(ModelMap model){
		System.out.println("logout....");
		return "logout";
	}
}
