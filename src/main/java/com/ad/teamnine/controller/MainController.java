package com.ad.teamnine.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ad.teamnine.service.CsvService;

import jakarta.annotation.Resource;

@Controller
public class MainController {
	private CsvService csvService;
	public MainController(CsvService csvService) {
		this.csvService = csvService;
	}
	@RequestMapping("/")
	public String home() {
		//csvService.readRecipeCsvFile("D:\\code\\ADProject\\recipe_final.csv");
		return "page1";
		
	}
}
