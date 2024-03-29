package com.ad.teamnine.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Member extends User{
	@Column
	private Double height;
	@Column
	private Double weight;
	@Column
	private Integer age;
	@Column
	private LocalDate birthdate;
	@Column
	private String gender;
	@Column
	private Double calorieIntake;
	@Column
	private List<Ingredient> shoppingList;
	
	@OneToMany(mappedBy = "member")
	private List<Recipe> savedRecipes;
	
	@OneToMany(mappedBy = "member")
	private List<Recipe> addedRecipes;
	
	@OneToMany(mappedBy = "member")
	private List<Review> reviews;
	
	@OneToMany(mappedBy = "member")
	private List<Report> reports;
	
	@OneToMany(mappedBy = "memberReported")
	private List<MemberReport> reportsToMember;
	
	// getter and setter
	public List<Ingredient> getShoppingList() {
		return shoppingList;
	}
	public void setShoppingList(List<Ingredient> shoppingList) {
		this.shoppingList = shoppingList;
	}
	public List<Report> getReports() {
		return reports;
	}
	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	public List<MemberReport> getReportsToMember() {
		return reportsToMember;
	}
	public void setReportsToMember(List<MemberReport> reportsToMember) {
		this.reportsToMember = reportsToMember;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthday) {
		this.birthdate = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Double getCalorieIntake() {
		return calorieIntake;
	}
	public void setCalorieIntake(Double calorieIntake) {
		this.calorieIntake = calorieIntake;
	}
	public List<Recipe> getSavedRecipes() {
		return savedRecipes;
	}
	public void setSavedRecipes(List<Recipe> savedRecipes) {
		this.savedRecipes = savedRecipes;
	}
	public List<Recipe> getAddedRecipes() {
		return addedRecipes;
	}
	public void setAddedRecipes(List<Recipe> addedRecipes) {
		this.addedRecipes = addedRecipes;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
