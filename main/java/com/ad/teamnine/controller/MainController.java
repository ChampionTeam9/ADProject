package com.ad.teamnine.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.annotation.Resource;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String home() {
		//csvService.readRecipeCsvFile("/Users/qing/Downloads/recipe_final.csv");
		return "page1";
		
	}
}