package com.ad.teamnine.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ad.teamnine.service.CsvService;

@Controller
@RequestMapping("/")
public class MainController {
	private final CsvService csvService;

	public MainController(CsvService csvService) {
		this.csvService = csvService;
	}
	@RequestMapping("/")
	public String home() {
		return "page1";
		
	}
	@RequestMapping("/read")
	public String processCsvFile() {
		// 传入CSV文件的路径
		csvService.readCsvFile("classpath:sample.csv");
		return "page1";
	}
}
