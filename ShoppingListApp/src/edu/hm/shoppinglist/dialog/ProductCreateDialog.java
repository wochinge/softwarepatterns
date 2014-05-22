package edu.hm.shoppinglist.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import edu.hm.shoppinglist.R;
import edu.hm.shoppinglist.callback.FailAsToastListener;
import edu.hm.shoppinglist.entities.Product;
import edu.hm.shoppinglist.entities.ShoppingList;
import edu.hm.shoppinglist.web.Webservice;
import edu.hm.shoppinglist.web.WebserviceImpl;

public class ProductCreateDialog extends DialogFragment {

	private OnRefreshCallback refresh;
	public static final String LIST_ID = "listId";
	public static final String LIST_NAME = "listName";
	public static final String LIST_TIMESTAMP = "listTimestamp";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflator = getActivity().getLayoutInflater();
		View inputView = inflator.inflate(R.layout.product_create_dialog, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
		alertDialogBuilder.setView(inputView);
		alertDialogBuilder.setTitle(R.string.enter_productname);

		final EditText userInput = (EditText) inputView
				.findViewById(R.id.productname_input);

		final EditText productNumber = (EditText) inputView
				.findViewById(R.id.productnumber_input);

		final Spinner unitSpinner = (Spinner) inputView.findViewById(R.id.units_spinner);

		alertDialogBuilder.setCancelable(false);
		alertDialogBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						if (!TextUtils.isEmpty(userInput.getText())
								&& !TextUtils.isEmpty(productNumber.getText())) {
							createProduct(userInput.getText().toString(),
									parseProductNumber(productNumber.getText()),
											unitSpinner.getSelectedItemPosition());
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

	return alertDialogBuilder.create();
	}

	public void setRefreshListener(final OnRefreshCallback refresh) {
		this.refresh = refresh;
	}


	private int parseProductNumber(final Editable input) {
		return Integer.parseInt(input.toString());
	}

	private ShoppingList getList() {
		Bundle arguments = getArguments();
		ShoppingList list = new ShoppingList();
		list.setId(arguments.getInt(LIST_ID));
		list.setName(arguments.getString(LIST_NAME));
		list.setTimestamp(arguments.getLong(LIST_TIMESTAMP));
		return list;
	}
	private void createProduct(final String listName, final int numberOf, final int unit) {
		Webservice service = new WebserviceImpl();
		Product product = new Product(listName, numberOf, unit);
		ShoppingList list = getList();

		service.createProduct(list, product, new FailAsToastListener(getActivity()));
		refresh.onRefresh();
	}
}
