package edu.hm.shoppinglist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import edu.hm.shoppinglist.callback.FailAsToastListener;
import edu.hm.shoppinglist.dialog.OnRefreshCallback;
import edu.hm.shoppinglist.dialog.ProductCreateDialog;
import edu.hm.shoppinglist.entities.ShoppingList;
import edu.hm.shoppinglist.fragments.ProductListFragment;
import edu.hm.shoppinglist.web.Webservice;
import edu.hm.shoppinglist.web.WebserviceImpl;

public class ProductListActivity extends Activity {

	private ProductListFragment productFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		productFragment = new ProductListFragment();
		productFragment.setArguments(getIntent().getExtras());
        getFragmentManager().beginTransaction()
                .add(R.id.product_container, productFragment).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.products, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.create_product:
				showProductCreatorDialog();
				break;
			case R.id.delete_list:
				deleteList();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void deleteList() {
		Webservice service = new WebserviceImpl();
		service.deleteShoppingList(productFragment.getShoppingList(), new FailAsToastListener(this));
		finish();

	}

	private void showProductCreatorDialog() {
		ProductCreateDialog dialog = new ProductCreateDialog();
		dialog.setRefreshListener(new RefreshListener());

		Bundle arguments = new Bundle();
		ShoppingList list = productFragment.getShoppingList();
		arguments.putInt(ProductCreateDialog.LIST_ID, list.getId());
		arguments.putString(ProductCreateDialog.LIST_NAME, list.getName());
		arguments.putLong(ProductCreateDialog.LIST_TIMESTAMP, list.getTimestamp());
		dialog.setArguments(arguments);

		dialog.show(getFragmentManager(), getClass().getSimpleName());
	}

	public class RefreshListener implements OnRefreshCallback {

		@Override
		public void onRefresh() {
			refresh();
		}

	}

	private void refresh() {
		productFragment.fillData();
	}

}
