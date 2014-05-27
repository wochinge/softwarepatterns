package edu.hm.shoppinglist.web;


import edu.hm.shoppinglist.callback.OnFailListener;
import edu.hm.shoppinglist.callback.OnResultListener;
import edu.hm.shoppinglist.entities.Product;
import edu.hm.shoppinglist.entities.ShoppingList;
import edu.hm.shoppinglist.web.requests.Delete;
import edu.hm.shoppinglist.web.requests.Get;
import edu.hm.shoppinglist.web.requests.Post;
import edu.hm.shoppinglist.web.requests.Put;

public class WebserviceImpl implements Webservice {

	public final static String SERVER_URL = "http://185.21.101.44:8080/sharedshopping/";
	public final static String LIST_URL = SERVER_URL + "lists/";
	public final static String ITEM_URL = "items/";

	public final static int TIMEOUT_IN_MILLIS = 5;

	@Override
	public void requestShoppingLists(
			OnResultListener<ShoppingList[]> resultListener,
			OnFailListener failListener) {
		Get<ShoppingList[]> listGet = new Get<ShoppingList[]>(resultListener, failListener, ShoppingList[].class);
		listGet.execute(LIST_URL);
	}

	@Override
	public void createShoppingList(ShoppingList list, OnFailListener failListener) {
		Post<ShoppingList> post = new Post<ShoppingList>(failListener);
		post.setPostObject(list, ShoppingList.class);
		post.execute(LIST_URL);
	}

	@Override
	public void deleteShoppingList(ShoppingList list, OnFailListener failListener) {
		Delete delete = new Delete(failListener);
		String url = getListUrl(list);
		delete.execute(url);

	}

	@Override
	public void requestProducts(ShoppingList list,
			OnResultListener<Product[]> resultListener,
			OnFailListener failListener) {
		Get<Product[]> listGet = new Get<Product[]>(resultListener, failListener, Product[].class);
		String url = getProductUrl(list);
		listGet.execute(url);
	}

	@Override
	public void createProduct(ShoppingList list, Product product,
			OnFailListener failListener) {
		Post<Product> post = new Post<Product>(failListener);
		post.setPostObject(product, Product.class);
		String url = getProductUrl(list);
		post.execute(url);

	}

	@Override
	public void deleteProduct(ShoppingList list, Product product,
			OnFailListener failListener) {
		Delete delete = new Delete(failListener);
		String url = getProductUrl(list, product);
		delete.execute(url);

	}

	@Override
	public void updateProduct(ShoppingList list, Product product,
			OnFailListener failListener) {
		Put<Product> put = new Put<>(failListener);
		put.setPutObject(product);
		String url = getProductUrl(list, product);
		put.execute(url);
	}

	private String getListUrl(final ShoppingList list) {
		return LIST_URL + String.valueOf(list.getId()) + "/";
	}

	private String getProductUrl(final ShoppingList list, final Product product) {
		return getListUrl(list) + ITEM_URL + String.valueOf(product.getId());
	}

	private String getProductUrl(final ShoppingList list) {
		return getListUrl(list) + ITEM_URL;
	}

}
