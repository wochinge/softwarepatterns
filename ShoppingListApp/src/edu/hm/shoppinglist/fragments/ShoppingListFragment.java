package edu.hm.shoppinglist.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.hm.shoppinglist.ProductListActivity;
import edu.hm.shoppinglist.R;
import edu.hm.shoppinglist.adapters.ShoppingListAdapter;
import edu.hm.shoppinglist.callback.FailAsToastListener;
import edu.hm.shoppinglist.callback.OnResultListener;
import edu.hm.shoppinglist.entities.ShoppingList;
import edu.hm.shoppinglist.web.Webservice;
import edu.hm.shoppinglist.web.WebserviceImpl;

public class ShoppingListFragment extends ListFragment {

	private ShoppingListAdapter adapter;

	@Override
	public void onResume() {
		super.onResume();
		fillData();
	}

	public class ListReceiver implements OnResultListener<ShoppingList[]> {

		@Override
		public void onResult(ShoppingList[] result) {
			adapter = new ShoppingListAdapter(getActivity(), R.layout.shopping_list, result);
			setListAdapter(adapter);
		}
	}

	public void fillData() {
		Webservice webservice = new WebserviceImpl();
		webservice.requestShoppingLists(new ListReceiver(), new FailAsToastListener(getActivity()));
		ListView view = getListView();
		view.setOnItemClickListener(new ListClickedListener());
	}

	private class ListClickedListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ShoppingList list = adapter.getListByPosition(position);
			Bundle productArguments = new Bundle();
	        productArguments.putString(ProductListFragment.LIST_NAME, list.getName());
	        productArguments.putInt(ProductListFragment.LIST_ID, list.getId());

	        Intent productActivity = new Intent(ShoppingListFragment.this.getActivity(), ProductListActivity.class);
	        productActivity.putExtras(productArguments);
	        startActivity(productActivity);
		}

	}
}
