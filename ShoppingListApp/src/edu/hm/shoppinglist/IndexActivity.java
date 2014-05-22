package edu.hm.shoppinglist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import edu.hm.shoppinglist.dialog.ListCreateDialog;
import edu.hm.shoppinglist.dialog.OnRefreshCallback;
import edu.hm.shoppinglist.fragments.ShoppingListFragment;

public class IndexActivity extends Activity {

	private ShoppingListFragment listFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		listFragment = (ShoppingListFragment) getFragmentManager().findFragmentById(R.id.list_fragment);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lists, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.create_list) {
			showListCreatorDialog();
		}
		return super.onOptionsItemSelected(item);
	}

	private void showListCreatorDialog() {
		ListCreateDialog dialog = new ListCreateDialog();
		dialog.setRefreshListener(new RefreshListener());
		dialog.show(getFragmentManager(), this.getClass().getSimpleName());
	}

	public class RefreshListener implements OnRefreshCallback {

		@Override
		public void onRefresh() {
			refresh();
		}

	}

	private void refresh() {
		listFragment.fillData();
	}

}
