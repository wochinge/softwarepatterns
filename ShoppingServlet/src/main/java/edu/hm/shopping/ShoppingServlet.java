package edu.hm.shopping;

import org.glassfish.jersey.server.ResourceConfig;

public class ShoppingServlet extends ResourceConfig {

	public ShoppingServlet() {
		super();
		packages("edu.hm.shopping");
	}
}
