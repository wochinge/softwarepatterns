package edu.hm.shoppinglist.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import edu.hm.shoppinglist.R;
import edu.hm.shoppinglist.callback.FailAsToastListener;
import edu.hm.shoppinglist.entities.ShoppingList;
import edu.hm.shoppinglist.web.Webservice;
import edu.hm.shoppinglist.web.WebserviceImpl;

public class ListCreateDialog extends DialogFragment {

	private OnRefreshCallback refresh;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflator = getActivity().getLayoutInflater();
		View inputView = inflator.inflate(R.layout.list_create_dialog, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
		alertDialogBuilder.setView(inputView);
		alertDialogBuilder.setTitle(R.string.enter_listname);

		final EditText userInput = (EditText) inputView
				.findViewById(R.id.listname_input);
		alertDialogBuilder.setCancelable(false);
		alertDialogBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						if (!TextUtils.isEmpty(userInput.getText())) {
							createShoppingList(userInput.getText().toString());
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
	private void createShoppingList(String listName) {
		Webservice service = new WebserviceImpl();
		ShoppingList list = new ShoppingList(listName);
		service.createShoppingList(list, new FailAsToastListener(getActivity()));
		refresh.onRefresh();
	}




}
