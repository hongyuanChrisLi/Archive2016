package com.example.test;

import com.example.Dog;
import com.example.model.*;

import foo.Employee;

import java.util.*;

public class ModelTest 
{
	public static void main (String[] args)
	{
		/*BeerExpert beer = new BeerExpert();
		List<String> beerlst = beer.getBrands("grey");
		System.out.println(Arrays.toString(beerlst.toArray()));*/
		
		Employee p = new Employee();
		p.setName("Evan");
		Dog dog = new Dog();
		dog.setName("Spike");
		p.setDog(dog);
		
		System.out.println(p.getDog().getName());

	}
}


