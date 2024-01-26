package com.ad.teamnine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ad.teamnine.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient,Integer>{

}
