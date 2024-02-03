package com.ad.teamnine.controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ad.teamnine.model.Recipe;
import com.ad.teamnine.model.Status;
import com.ad.teamnine.service.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	
	@Autowired
    private RecipeService recipeService; 
	
	@GetMapping("/create")
    public String showAddRecipeForm(Model model) {
      
        return "createRecipesPage";
    }

	@PostMapping("/create")
    public String addRecipe(@RequestParam("name") String name,
			@RequestParam("description") String description, 
			@RequestParam("servings") int servings,
			@RequestParam("preparationTime") int preparationTime,
			@RequestParam("timeUnit") String timeUnit,
			@RequestParam("ingredients") List<String> ingredients, 
			@RequestParam("steps")List<String> steps,
			@RequestParam("image") MultipartFile pictureFile,
			@RequestParam("tags") List<String> tags,
			@RequestParam("status") String status,
			Model model) {
		
		Recipe recipe = new Recipe();
        
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setServings(servings);
        
        if (timeUnit.equals("Minutes")) {
        	recipe.setPreparationTime(preparationTime);
        }
        else {
        	preparationTime = preparationTime * 60;
        	recipe.setPreparationTime(preparationTime);
        }
        
        recipe.setIngredients(ingredients);
        recipe.setSteps(steps);
        
        // 获取图片文件名
        String fileName = pictureFile.getOriginalFilename();

        // 保存图片到服务器的某个位置
        String filePath = "/path/to/save/images/" + fileName;

        try {
            // 写入文件
            pictureFile.transferTo(new File(filePath));

            // 将图片路径保存到 Recipe 对象中
            recipe.setImage(filePath);
        } catch (IOException e) {
            // Handle the exception (e.g., log it)
            e.printStackTrace();
        }

        
        
        recipe.setStatus(Status.valueOf(status));
        recipe.setTags(tags);

        
        RecipeService.createRecipe(recipe);

        return "redirect:/recipe/list";
    }
	
	
	
	
	@GetMapping("/list")
	public String showAddRecipeList(Model model) {
	    List<Recipe> recipeList = RecipeService.getAllRecipes();
	    model.addAttribute("recipes", recipeList);
	    
	    return "recipeListPage";
	}
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") Integer id) {
        // 通过服务层执行删除操作
        RecipeService.deleteRecipe(id);
        
        return ResponseEntity.ok("Recipe deleted successfully");
    }
	
	@RequestMapping("/edit/{id}")
	public String getUpdateUserPage(@PathVariable("id") Integer id, Model model) {
		
		Recipe recipe = RecipeService.getRecipeById(id);
	    model.addAttribute("recipe", recipe);
		
		return "updateRecipesPage";
	}
	
	@PostMapping("/edit")
    public String updateRecipe(@ModelAttribute Recipe recipe) {
		
        RecipeService.updateRecipe(recipe);

        return "redirect:/recipe/list";
    }
	
	@GetMapping("/view/{id}")
	public String viewRecipe(@PathVariable("id") Integer id, Model model) {
	    Recipe recipe = RecipeService.getRecipeById(id);
	    model.addAttribute("recipe", recipe);
	    return "viewPage"; 
	}
	

}