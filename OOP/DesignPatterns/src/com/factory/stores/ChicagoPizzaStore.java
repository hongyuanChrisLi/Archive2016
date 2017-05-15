package com.factory.stores;

import com.factory.framework.*;
import com.factory.pizza.*;

public class ChicagoPizzaStore extends PizzaStore {

	Pizza pizza;

	@Override
	public Pizza createPizza(String type) {

		if (type.equals("cheese")) {
			pizza = new ChicagoStyleCheesePizza();
		} else {
			pizza = null;
		}
		return pizza;
	}
}