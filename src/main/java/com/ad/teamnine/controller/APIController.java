package com.ad.teamnine.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ad.teamnine.model.Ingredient;
import com.ad.teamnine.model.IngredientInfo;
import com.ad.teamnine.model.Member;
import com.ad.teamnine.model.ShoppingListItem;
import com.ad.teamnine.service.CsvService;

@RestController
@RequestMapping("/api")
public class APIController {
	private final CsvService csvService;

	public APIController(CsvService csvService) {
		this.csvService = csvService;
	}
	
	@RequestMapping("/read")
	public void processCsvFile() {
		// 传入CSV文件的路径
		csvService.readCsvFile("classpath:sample.csv");
	}
	
	@GetMapping("/nutrition")
	public IngredientInfo getNutritionInfo() {
		String appId = "a0eca928";
        String appKey = "2791c4e7ff627b1a94a4a8e41a6e0a14";
        String url = "https://api.edamam.com/api/nutrition-data?app_id=" + appId +
                     "&app_key=" + appKey +
                     "&nutrition-type=cooking&ingr=1banana";
		RestTemplate restTemplate = new RestTemplate();
		IngredientInfo ingredient = restTemplate.getForObject(url, IngredientInfo.class);
		return ingredient;
	}
}
