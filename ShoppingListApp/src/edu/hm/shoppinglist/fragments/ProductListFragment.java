package edu.hm.shoppinglist.fragments;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import edu.hm.shoppinglist.R;
import edu.hm.shoppinglist.adapters.ProductListAdapter;
import edu.hm.shoppinglist.callback.FailAsToastListener;
import edu.hm.shoppinglist.callback.OnResultListener;
import edu.hm.shoppinglist.entities.Product;
import edu.hm.shoppinglist.entities.ShoppingList;
import edu.hm.shoppinglist.web.Webservice;
import edu.hm.shoppinglist.web.WebserviceImpl;

public class ProductListFragment extends ListFragment {

	public static final String LIST_NAME = "listname";
	public static final String LIST_ID = "listId";

	private ProductListAdapter adapter;
	private ShoppingList list;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Bundle listParameters = getArguments();
		initList(listParameters.getString(LIST_NAME), listParameters.getInt(LIST_ID));
		initOnItemClickListener();
		fillData();
	}

	private void initOnItemClickListener() {
		registerForContextMenu(getListView());
		ListView listView = getListView();
		listView.setOnItemClickListener(new OnProductClickedListener());
	}

	private void initList(final String name, final int id) {
		list = new ShoppingList(name);
		list.setId(id);
	}

	public void fillData() {
		Webservice webservice = new WebserviceImpl();
		webservice.requestProducts(list, new ProductListListener(), new FailAsToastListener(getActivity()));
	}

	public class ProductListListener implements OnResultListener<Product[]> {

		@Override
		public void onResult(Product[] result) {
			adapter = new ProductListAdapter(getActivity(), R.layout.product_list, result);
			setListAdapter(adapter);
		}

	}

	public ShoppingList getShoppingList() {
		return list;
	}


	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.delete_product:
			deleteProduct(adapter.getProductByPosition(info.id));
			break;
		case R.id.update_product:
			showUpdateDialog(adapter.getProductByPosition(info.id));
			break;
		case R.id.product_bought:
			buyProduct(adapter.getProductByPosition(info.id));
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void buyProduct(Product productByPosition) {
		productByPosition.setIsBought(true);
		updateProduct(productByPosition);
	}

	private void deleteProduct(Product product) {
		Webservice service = new WebserviceImpl();
		service.deleteProduct(list, product, new FailAsToastListener(getActivity()));
		fillData();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.product_context_menu, menu);
	}

	public class OnProductClickedListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			view.showContextMenu();
		}
	}


	private void showUpdateDialog(final Product product) {
			LayoutInflater inflator = getActivity().getLayoutInflater();
			View inputView = inflator.inflate(R.layout.product_update_dialog, null);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
			alertDialogBuilder.setView(inputView);
			alertDialogBuilder.setTitle(R.string.product_number);

			final EditText numberInput = (EditText) inputView
					.findViewById(R.id.product_new_number_input);

			alertDialogBuilder.setCancelable(false);
			alertDialogBuilder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int id) {
							if (!TextUtils.isEmpty(numberInput.getText())) {
								updateProduct(product, parseProductNumber(numberInput.getText()));
							} else {
								Toast.makeText(getActivity(),
										getText(R.string.no_text),
										Toast.LENGTH_SHORT).show();
							}
						}
					});
			alertDialogBuilder.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
							});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

	private int parseProductNumber(final Editable input) {
		return Integer.parseInt(input.toString());
	}

	private void updateProduct(final Product product, final int numberOf) {
		product.setNumberOf(numberOf);
		updateProduct(product);
	}
	private void updateProduct(final Product product) {
		Webservice service = new WebserviceImpl();

		service.updateProduct(list, product, new FailAsToastListener(getActivity()));
		fillData();
	}
}
