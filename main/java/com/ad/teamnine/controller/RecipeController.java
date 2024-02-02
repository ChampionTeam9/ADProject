package com.ad.teamnine.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ad.teamnine.model.Recipe;
import com.ad.teamnine.repository.RecipeRepository;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	@Autowired
	RecipeRepository recipeRe;
	
	
	@PostMapping("/search")
	public String searchRecipe(@RequestParam(name = "query")String query,Model model) {
		
		List<Recipe> results = recipeRe.findByNameContaining(query);
        model.addAttribute("results", results);
		return "result";
	}
}