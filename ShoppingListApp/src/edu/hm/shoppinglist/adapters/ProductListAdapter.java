package edu.hm.shoppinglist.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.hm.shoppinglist.R;
import edu.hm.shoppinglist.entities.Product;

public class ProductListAdapter extends ArrayAdapter<Product> {

	private final Context context;
	private TextView nameView;
	private TextView numberView;
	private TextView unitView;

	public ProductListAdapter(Context context, int textViewResourceId,
			Product[] objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.product_list, null);
		Product product = getItem(position);

		nameView = (TextView) view.findViewById(R.id.product_name);
		nameView.setText(product.getName());

		numberView = (TextView) view.findViewById(R.id.product_number);
		numberView.setText(String.valueOf(product.getNumberOf()));

		unitView = (TextView) view.findViewById(R.id.product_unit);
		unitView.setText(context.getResources().getStringArray(R.array.units)[product.getUnit()]);
		setCrossedOut(product.getIsBought());
		return view;
	}

	private void setCrossedOut(final boolean crossedOut) {
		if (crossedOut) {
			nameView.setPaintFlags(nameView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			numberView.setPaintFlags(numberView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			unitView.setPaintFlags(unitView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}
	}

	public Product getProductByPosition(long position) {
		int castetPosition = (int) position;
		return getItem(castetPosition);
	}
}
