package edu.hm.shopping.entities;

import edu.hm.shopping.db.DatabaseService;
import edu.hm.shopping.db.StubDatabase;

public class Product {

	private int id;
	private String name;
	private int numberOf;
	private int unit;
	private boolean isBought;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getUnit() {
		return unit;
	}

	public void setUnit(final int unit) {
		DatabaseService service = new StubDatabase();
		if (service.isUnitValid(unit)) {
			this.unit = unit;
		} else {
			throw new IllegalArgumentException(unit + " is an invalid value for an unit");
		}
	}

	public boolean getIsBought() {
		return isBought;
	}

	public void setIsBought(boolean isBought) {
		this.isBought = isBought;
	}
}
