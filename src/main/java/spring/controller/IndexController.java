package spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.model.LoggedUser;

@Controller
public class IndexController{
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		
		return "index";
	}
	
	@RequestMapping(value = "/api/mytoken", method = RequestMethod.GET)
	public @ResponseBody LoggedUser myTokens() {
		
		LoggedUser loggedUser = null;
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth.getPrincipal()!= "anonymousUser")
			 loggedUser  = (LoggedUser)auth.getPrincipal();
		else
			loggedUser = new LoggedUser("Not Found", "NA", false, false, false, false, auths, 0, "Not Found");
		
		return loggedUser;
	}

}
