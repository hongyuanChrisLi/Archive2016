package com.decorator;

import com.decorator.framework.*;

public class HouseBlend extends Beverage {

	public HouseBlend() {

		description = "House Blend Coffee";
	}

	public double cost() {
		return .89;
	}
}