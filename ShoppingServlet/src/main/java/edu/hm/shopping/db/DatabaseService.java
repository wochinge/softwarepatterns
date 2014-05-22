package edu.hm.shopping.db;

import java.util.Collection;

import edu.hm.shopping.entities.ListItem;
import edu.hm.shopping.entities.ShoppingList;

public interface DatabaseService {

	Collection<ShoppingList> getLists();
	
	ShoppingList createList(final ShoppingList list);
	
	void deleteList(final int listId);
	
	Collection<ListItem> getProducts(final int listId);
	
	ListItem createProduct(final int listId, ListItem product);
	
	void updateProduct(final int listId, final int productId, final ListItem product);
	
	void deleteProduct(final int listId, final int productId);
	
	boolean isUnitValid(final int unit);
}
