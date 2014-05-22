package edu.hm.shopping.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import edu.hm.shopping.entities.ListItem;
import edu.hm.shopping.entities.ShoppingList;

/**
 * Just a stub database.
 * Please replace with a real one.
 *
 */
public class StubDatabase implements DatabaseService {

	private static HashMap<Integer, ShoppingList> lists = new HashMap<Integer, ShoppingList>();
	private static HashMap<Integer, HashMap<Integer, ListItem>> products = new HashMap<Integer, HashMap<Integer,ListItem>>();
	private static HashMap<Integer, Integer> possibleProductId = new HashMap<Integer, Integer>();
	private static int id = 1;
	private static HashMap<Integer, String> units = new HashMap<Integer, String>() {
		{
			put(0, "Piece");
			put(1, "Gram");
			put(2, "Kilogram");
			put(3, "Millilitre");
			put(4, "Litre");
		};
	};
	
	public Collection<ShoppingList> getLists() {
		return lists.values();
	}

	public ShoppingList createList(final ShoppingList list) {
		list.setId(id);

		lists.put(id, list);
		products.put(id, new HashMap<Integer, ListItem>());
		id++;
		return list;
		
	}

	public void deleteList(final int listId) {
		validateListId(listId);
		lists.remove(listId);
		
	}

	public Collection<ListItem> getProducts(final int listId) {
		validateListId(listId);
		return products.get(listId).values();
	}

	public ListItem createProduct(final int listId, final ListItem product) {
		validateListId(listId);
		ShoppingList list = lists.get(listId);
		List<Integer> productReferences = list.getProductIds();
		
		int productId = getPossibleProductId(listId);
		productReferences.add(productId);
		
		product.setId(productId);
		products.get(listId).put(productId, product);
		
		incrementPossibleProductId(listId);
		return product;
		
	}

	public void updateProduct(final int listId, final int productId, ListItem product) {
		validateListId(listId);
		validateProductId(listId, productId);
		product.setId(productId);
		products.get(listId).put(productId, product);
	}

	public void deleteProduct(final int listId, final int productId) {
		validateListId(listId);
		validateProductId(listId, productId);
		
		List<Integer> productReferences = lists.get(listId).getProductIds();
		productReferences.remove(productReferences.indexOf(productId));
		
		products.get(listId).remove(productId);
		
	}

	private final int getPossibleProductId(final int listId) {
		Integer result = possibleProductId.get(listId);
		if (result == null) {
			return 1;
		}
		return result;
	}
	
	private void incrementPossibleProductId(final int listId) {
		Integer result = possibleProductId.get(listId);
		if (result == null) {
			possibleProductId.put(listId, 2);
		} 
		else {
			possibleProductId.put(listId, ++result);
		}

	}
	
	private void validateListId(final int listId) {
		if (!lists.containsKey(listId)) {
			throw new IllegalArgumentException("List " + listId + " does not exist");
		}
	}
	
	private void validateProductId(final int listId, final int productId) {
		if (!products.get(listId).containsKey(productId)) {
			throw new IllegalArgumentException("Product " + productId + " is not existing");
		}
	}

	public boolean isUnitValid(final int unit) {
		return units.containsKey(unit);
	}
}