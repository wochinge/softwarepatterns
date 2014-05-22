package edu.hm.shoppinglist.entities;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {

	private int id;
	private String name;
	private long timestamp;
	private List<Integer> productIds;

	public ShoppingList() {

	}

	public ShoppingList(final String name) {
		productIds = new ArrayList<Integer>();
		this.name = name;
		this.timestamp = System.currentTimeMillis();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}
}
