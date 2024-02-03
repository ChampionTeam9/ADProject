package com.ad.teamnine.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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
	
	// get all unique tags
		public Set<String> getAllUniqueTags() {
			List<String> tagLists = recipeRepo.findAllDistinctTags();
			Set<String> uniqueTags = new HashSet<>();

			for (String tags : tagLists) {
				uniqueTags.addAll(Arrays.asList(tags.split(",")));
			}

			return uniqueTags;
		}

		public Set<String> getRandomUniqueTags(int count) {
			List<String> allTags = new ArrayList<>(getAllUniqueTags());
			Collections.shuffle(allTags, new Random());
			return allTags.stream().limit(count).collect(Collectors.toCollection(LinkedHashSet::new));
		}
		
		public List<String> findMatchingTags(String keyword) {
	        Set<String> allUniqueTags = getAllUniqueTags();

	        
	        List<String> matchingTags = allUniqueTags.stream()
	                .filter(tag -> tag.toLowerCase().contains(keyword.toLowerCase()))
	                .collect(Collectors.toList());

	        return matchingTags;
	    }
}
