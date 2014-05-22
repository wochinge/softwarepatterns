package edu.hm.shoppinglist.web.requests;


import org.springframework.web.client.RestTemplate;

import edu.hm.shoppinglist.callback.OnFailListener;
import edu.hm.shoppinglist.web.WebTask;

/**
 * Klasse für alle GET-Aufrufe.
 * @author Tobias Wochinger
 *
 */
public class Delete extends WebTask<String, Void, Void> {

	public static final String DELETE = "DELETE";

	public Delete(OnFailListener failListener) {
		super(null, failListener);
	}

	@Override
	protected Void doInBackground(String... params) {
		RestTemplate rest = new RestTemplate();
		rest.delete(params[URL_PARAM]);

		return null;
	}
}
