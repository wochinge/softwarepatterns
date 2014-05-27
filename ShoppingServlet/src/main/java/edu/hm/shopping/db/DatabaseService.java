package edu.hm.shopping.db;

import java.util.Collection;

import edu.hm.shopping.entities.Product;
import edu.hm.shopping.entities.ShoppingList;

public interface DatabaseService {

	Collection<ShoppingList> getLists();
	
	ShoppingList createList(final ShoppingList list);
	
	void deleteList(final int listId);
	
	Collection<Product> getProducts(final int listId);
	
	Product createProduct(final int listId, Product product);
	
	void updateProduct(final int listId, final int productId, final Product product);
	
	void deleteProduct(final int listId, final int productId);
	
	boolean isUnitValid(final int unit);
}
