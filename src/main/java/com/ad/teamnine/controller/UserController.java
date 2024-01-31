package com.ad.teamnine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ad.teamnine.model.Recipe;
import com.ad.teamnine.service.RecipeService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("/shoppingList/add")
	public String addShoppingListIngredient(Model model) {
		Recipe recipe = recipeService.getRecipeById(1);
		model.addAttribute("recipe", recipe);
		return "UserViews/addShoppingListIngredientPage";
	}
}
