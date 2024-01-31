package com.ad.teamnine;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ad.teamnine.model.Ingredient;
import com.ad.teamnine.model.Member;
import com.ad.teamnine.model.Recipe;
import com.ad.teamnine.repository.IngredientRepository;
import com.ad.teamnine.repository.MemberRepository;
import com.ad.teamnine.repository.RecipeRepository;

@SpringBootApplication
public class AdProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRun(MemberRepository memberRepo, IngredientRepository ingrRepo, RecipeRepository recipeRepo) {
		return args -> {
			LocalDate date1 = LocalDate.of(2000, 1, 8);
			Member member1 = new Member(1, "member1Username", "member1Password!", 170, 65.3, date1, "Male");
			memberRepo.save(member1);
			List<Ingredient> member1ShoppingList = member1.getShoppingList();
			
			Ingredient ingredient1 = new Ingredient(1, "1banana", 1.26, 102.97, 26.38, 14.11, 1.16, 0, 0.13);
			ingrRepo.save(ingredient1);
			
			Recipe recipe1 = new Recipe(1, "recipe1Name", "recipe1Description", member1);
			recipe1.getIngredients().add(ingredient1);
			recipeRepo.save(recipe1);
		};
	}
}
