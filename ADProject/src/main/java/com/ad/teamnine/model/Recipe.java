package com.ad.teamnine.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Recipe {
	@Id
	private Integer id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private Double rating;
	@Column
	private Integer numberOfSaved;
	@Column
	private Integer preparationTime;
	@Column
	private Integer servings;
	@Column
	private Integer numberOfSteps;
	@Column
	private Integer healthScore;
	@Column
	private String notes;
	@Column
	private String image;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column
	private Double calories;
	@Column
	private Double protein;
	@Column
	private Double carbohydrate;
	@Column
	private Double sugar;
	@Column
	private Double sodium;
	@Column
	private Double fat;
	@Column
	private Double saturateFat;
	@ElementCollection
	private List<String> steps;
	
	@ManyToMany(mappedBy = "recipes")
	private List<Ingredient> ingredients;
	@ManyToMany(mappedBy = "recipes")
	private List<Tag> tags;
	@OneToMany(mappedBy = "recipe")
	private List<Review> reviews;
	@ManyToOne
	private Member member;
	@OneToMany(mappedBy = "recipeReported")
	private List<RecipeReport> recipesToReport;
	
	// getter and setter
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Integer getNumberOfSaved() {
		return numberOfSaved;
	}
	public void setNumberOfSaved(Integer numberOfSaved) {
		this.numberOfSaved = numberOfSaved;
	}
	public Integer getNumberOfSteps() {
		return numberOfSteps;
	}
	public void setNumberOfSteps(Integer numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}
	public Integer getHealthScore() {
		return healthScore;
	}
	public void setHealthScore(Integer healthScore) {
		this.healthScore = healthScore;
	}
	public List<String> getSteps() {
		return steps;
	}
	public void setSteps(List<String> steps) {
		this.steps = steps;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Integer getPreparationTime() {
		return preparationTime;
	}
	public void setPreparationTime(Integer preparationTime) {
		this.preparationTime = preparationTime;
	}
	public Integer getServings() {
		return servings;
	}
	public void setServings(Integer servings) {
		this.servings = servings;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Double getCalories() {
		return calories;
	}
	public void setCalories(Double calories) {
		this.calories = calories;
	}
	public Double getProtein() {
		return protein;
	}
	public void setProtein(Double protein) {
		this.protein = protein;
	}
	public Double getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(Double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public Double getSugar() {
		return sugar;
	}
	public void setSugar(Double sugar) {
		this.sugar = sugar;
	}
	public Double getSodium() {
		return sodium;
	}
	public void setSodium(Double sodium) {
		this.sodium = sodium;
	}
	public Double getFat() {
		return fat;
	}
	public void setFat(Double fat) {
		this.fat = fat;
	}
	public Double getSaturateFat() {
		return saturateFat;
	}
	public void setSaturateFat(Double saturateFat) {
		this.saturateFat = saturateFat;
	}
}
