package com.ad.teamnine.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.teamnine.repository.RecipeRepository;
import com.ad.teamnine.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	RecipeRepository recipeRepo;

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
	    return allTags.stream()
	            .limit(count)
	            .collect(Collectors.toCollection(LinkedHashSet::new));
	}
	
}
