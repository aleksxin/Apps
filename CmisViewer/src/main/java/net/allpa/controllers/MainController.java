package net.allpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@RequestMapping(value={"/index","/"})
	public String index(@RequestParam(value="name",required=false,defaultValue="Worls")String name, Model model){
	model.addAttribute("name",name);
	return "list";
	}
	
	@RequestMapping(value={"/login"})
	public String login(Model model){

	return "login";
	}
}
