package com.ad.teamnine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ad.teamnine.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe,Integer>{
	
	@Query("SELECT DISTINCT r.tags FROM Recipe r")
	static
    List<String> findAllDistinctTags() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
