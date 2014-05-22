package edu.hm.shoppinglist.entities;


public class Product {

	private String name;
	private int numberOf;
	private int id;
	private int unit;
	private boolean isBought;

	public Product() {

	}

	public Product(final String name, final int numberOf, final int unit) {
		this.name = name;
		this.numberOf = numberOf;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOf() {
		return numberOf;
	}

	public void setNumberOf(int numberOf) {
		this.numberOf = numberOf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public boolean getIsBought() {
		return isBought;
	}

	public void setIsBought(boolean bought) {
		this.isBought = bought;
	}
}
