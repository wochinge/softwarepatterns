package edu.hm.shoppinglist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.hm.shoppinglist.R;
import edu.hm.shoppinglist.entities.ShoppingList;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingList> {

	private final Context context;

	public ShoppingListAdapter(Context context, int textViewResourceId,
			ShoppingList[] objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.shopping_list, null);
		ShoppingList list = getItem(position);
		TextView nameView = (TextView) view.findViewById(R.id.listName);
		nameView.setText(list.getName());
		return view;
	}

	public ShoppingList getListByPosition(long position) {
		int castetPosition = (int) position;
		return getItem(castetPosition);
	}
}
