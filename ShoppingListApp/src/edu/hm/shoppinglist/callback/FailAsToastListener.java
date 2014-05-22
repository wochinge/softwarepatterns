package edu.hm.shoppinglist.callback;

import android.app.Activity;
import android.widget.Toast;

public class FailAsToastListener implements OnFailListener {

	private final Activity activity;

	public FailAsToastListener(final Activity activity) {
		this.activity = activity;
	}
	@Override
	public void onFail(Exception e) {
		Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
	}

}
