package com.example;

public class Dog {
	private String breed;
	private String name;
	
	public Dog () {}
	
	public Dog(String breed) {
		this.breed = breed;
	}

	public String getBreed() {
		return breed;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getName () {
		return name;
	}
}