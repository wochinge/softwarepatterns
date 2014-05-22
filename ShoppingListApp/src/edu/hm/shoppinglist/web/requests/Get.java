package edu.hm.shoppinglist.web.requests;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import edu.hm.shoppinglist.callback.OnFailListener;
import edu.hm.shoppinglist.callback.OnResultListener;
import edu.hm.shoppinglist.utils.WebserviceException;
import edu.hm.shoppinglist.web.WebTask;

/**
 * Klasse für alle GET-Aufrufe.
 * @author Tobias Wochinger
 *
 */
public class Get<Result> extends WebTask<String, Void, Result> {

	private final Class<Result> resultClass;

	public Get(OnResultListener<Result> resultListener, OnFailListener failListener, Class<Result> result) {
		super(resultListener, failListener);
		resultClass = result;
	}

	@Override
	protected Result doInBackground(String... params) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		ResponseEntity<Result> result = rest.getForEntity(params[URL_PARAM], resultClass);
		validateResponseCode(result.getStatusCode());
		return result.getBody();
	}

	@Override
	protected void validateResponseCode(final HttpStatus status) {
		if (status != HttpStatus.OK) {
			cancel(new WebserviceException("Wrong Response: " + status));
		}
	}
}
