package com.ad.teamnine.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
public class Ingredient {
	@Id
	private Integer id;
	@Column
	private String foodText;
	@Column
	private Double protein;
	@Column
	private Double calories;
	@Column
	private Double carbohydrate;
	@Column
	private Double sugar;
	@Column
	private Double sodium;
	@Column
	private Double fat;
	@Column
	private Double saturatedFat;
	@ManyToMany
	private List<Recipe> recipes;
}
