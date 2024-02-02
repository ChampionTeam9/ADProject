package com.ad.teamnine.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ad.teamnine.model.*;
import com.ad.teamnine.repository.MemberRepository;
import com.ad.teamnine.repository.RecipeRepository;

@Service
public class CsvService {
	@Autowired
	RecipeRepository recipeRepo;
	@Autowired
	MemberRepository memberRepo;
	public void readRecipeCsvFile(String filePath) {
		try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
			String[] fields;
			int i = 0;
			reader.readNext();
			while ((fields = reader.readNext()) != null) {
				try {
					Recipe recipe = new Recipe();
					// name
					recipe.setName(fields[0]);
					// id
					recipe.setId(Integer.parseInt(fields[1]));
					// preparation time
					recipe.setPreparationTime(Integer.parseInt(fields[2]));
					// member
					Member member = new Member();
					member.setId(Integer.parseInt(fields[3]));
					memberRepo.save(member);
					recipe.setMember(member);
					// last update date
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
					LocalDate localDate = LocalDate.parse(fields[4], formatter);
					recipe.setLastUpdateDate(localDate);
					// tags
					List<String> tags = new ArrayList<>();
					tags = extractStrings(fields[5]);
					recipe.setTags(tags);
					// number of steps
					recipe.setNumberOfSteps(Integer.parseInt(fields[7]));
					// steps
					List<String> steps = new ArrayList<>();
					steps = extractStrings(fields[8]);
					recipe.setSteps(steps);
					// description
					String description = fields[9];
					int maxLength = 255;
					if (description.length() > maxLength) {
						description = description.substring(0, maxLength);
					}
					recipe.setDescription(description);
					// ingredients
					List<String> ingredients = new ArrayList<>();
					ingredients = extractStrings(fields[10]);
					recipe.setIngredients(ingredients);
					// number of ingredients
					recipe.setNumberOfIngredients(Integer.parseInt(fields[11]));
					// servings
					recipe.setServings(Integer.parseInt(fields[14]));
					// average rating
					recipe.setRating(Double.parseDouble(fields[16]));
					// number of reviews
					recipe.setNumberOfReviews(Integer.parseInt(fields[17]));
					// calories
					recipe.setCalories(Double.parseDouble(fields[18]));
					// fat
					recipe.setFat(Double.parseDouble(fields[19]));
					// sugar
					recipe.setSugar(Double.parseDouble(fields[20]));
					// sodium
					recipe.setSodium(Double.parseDouble(fields[21]));
					// protein
					recipe.setProtein(Double.parseDouble(fields[22]));
					// saturate fat
					recipe.setSaturateFat(Double.parseDouble(fields[23]));
					// carbohydrate
					recipe.setCarbohydrate(Double.parseDouble(fields[24]));
					// health score
					Integer healthScore = 0;
					if(Double.parseDouble(fields[19])>=15&&Double.parseDouble(fields[19])<30){
						healthScore+=1;
					}
					if(Double.parseDouble(fields[20])<10) {
						healthScore+=1;
					}
					if(Double.parseDouble(fields[21])<33){
						healthScore+=1;
					}
					if(Double.parseDouble(fields[22])>=10&&Double.parseDouble(fields[22])<15){
						healthScore+=1;
					}
					if(Double.parseDouble(fields[23])<10){
						healthScore+=1;
					}
					if(Double.parseDouble(fields[24])>=55&&Double.parseDouble(fields[19])<75){
						healthScore+=1;
					}
					recipe.setHealthScore(healthScore);
					// status
					recipe.setStatus(Status.CREATED);
					// number of members who saved this recipe
					recipe.setNumberOfSaved(0);
					// notes
					recipe.setNotes("no notes");
					recipeRepo.save(recipe);
				} catch (Exception e) {
					continue;
				}
				i++;
				if (i > 50) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
    private static List<String> extractStrings(String input) {
        List<String> extractedList = new ArrayList<>();
        input = input.substring(2, input.length() - 2);
        String[] parts = input.split("', '");   
        for (String part : parts) {
            extractedList.add(part);
        }
        return extractedList;
    }
}
