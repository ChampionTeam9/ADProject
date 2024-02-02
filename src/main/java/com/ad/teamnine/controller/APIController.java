package com.ad.teamnine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ad.teamnine.service.CsvService;

@RestController
@RequestMapping("/api")
public class APIController {
	private final CsvService csvService;

	public APIController(CsvService csvService) {
		this.csvService = csvService;
	}
}
