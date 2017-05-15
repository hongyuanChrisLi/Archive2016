package com.factory.stores;

import com.factory.framework.*;
import com.factory.pizza.*;

public class NYPizzaStore extends PizzaStore {

	Pizza pizza;

	@Override
	public Pizza createPizza(String type) {

		if (type.equals("cheese")) {
			pizza = new NYStyleCheesePizza();
		} else {
			pizza = null;
		}
		return pizza;
	}
}