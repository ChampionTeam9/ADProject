package com.ad.teamnine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ad.teamnine.model.Recipe;
import com.ad.teamnine.model.Status;
import com.ad.teamnine.repository.RecipeRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class RecipeService {
	
    private static RecipeRepository recipeRepo;
	
	@Autowired
    public RecipeService(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }
	
	// create new recipe
	public static void createRecipe(Recipe newRecipe) {
		recipeRepo.save(newRecipe);
		return;
	}
	
	// update exist recipe
	public static void updateRecipe(Recipe newRecipe) {
		recipeRepo.save(newRecipe);
		return;
	}
	
	// delete specific recipe by id
	public static void deleteRecipe(Integer id) {
		 try {
		        recipeRepo.deleteById(id);
		        System.out.println("Recipe with ID " + id + " has been deleted");
		    } 
		 catch (EmptyResultDataAccessException e) {
		        System.out.println("Recipe with ID " + id + " does not exist");
		    }
	}
	
	// get specific recipe by id
	public static Recipe getRecipeById(Integer id) {
		Optional<Recipe> recipe = recipeRepo.findById(id);
		return recipe.orElse(null);
	};
	
	// set recipe to public
	public void setStatusToPublicById(Integer id){
		Recipe recipe = recipeRepo.findById(id).orElse(null);
        if (recipe != null) {
            recipe.setStatus(Status.Public);
            recipeRepo.save(recipe);
        } else {
            System.out.println("Recipe with ID " + id + " not found");
        }
	}
	
	// set recipe to private
	public void setStatusToPrivateById(Integer id){
		Recipe recipe = recipeRepo.findById(id).orElse(null);
        if (recipe != null) {
            recipe.setStatus(Status.Private);
            recipeRepo.save(recipe);
        } else {
            System.out.println("Recipe with ID " + id + " not found");
        }
	}
	
	public static List<Recipe> getAllRecipes() {
        return recipeRepo.findAll();
    }
}
