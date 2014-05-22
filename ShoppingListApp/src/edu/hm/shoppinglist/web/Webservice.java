package edu.hm.shoppinglist.web;

import edu.hm.shoppinglist.callback.OnFailListener;
import edu.hm.shoppinglist.callback.OnResultListener;
import edu.hm.shoppinglist.entities.Product;
import edu.hm.shoppinglist.entities.ShoppingList;



/**
 * Schnittstelle für die aufrufbaren Webservices.
 *
 * @author Tobias Wochinger
 */
public interface Webservice {

	public void requestShoppingLists(final OnResultListener<ShoppingList[]> resultListener, final OnFailListener failListener);

	public void createShoppingList(final ShoppingList list, final OnFailListener failListener);

	public void deleteShoppingList(final ShoppingList list, final OnFailListener failListener);

	public void requestProducts(final ShoppingList list, final OnResultListener<Product[]> resultListener, final OnFailListener failListener);

	public void createProduct(final ShoppingList list, final Product product, final OnFailListener failListener);

	public void deleteProduct(final ShoppingList list, final Product product, final OnFailListener failListener);

	public void updateProduct(final ShoppingList list, final Product product, final OnFailListener failListener);

}
