package org.bookarchive.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
	
	@GetMapping(value = "/home")
	public String getHomePage() {
		return "home";
	}

}
