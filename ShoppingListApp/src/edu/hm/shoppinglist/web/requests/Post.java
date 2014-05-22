package edu.hm.shoppinglist.web.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import edu.hm.shoppinglist.callback.OnFailListener;
import edu.hm.shoppinglist.utils.WebserviceException;
import edu.hm.shoppinglist.web.WebTask;

/**
 * Klasse für alle Post-Webaufrufe
 * @author Tobias Wochinger
 *
 */
public class Post<Input> extends WebTask<String, Void, Input> {

	private Input postObject;
	private Class<Input> inputClass;

	public Post(OnFailListener failListener) {
		super(null, failListener);
	}

	public void setPostObject(Input postObject, Class<Input> inputClass) {
		this.postObject = postObject;
		this.inputClass = inputClass;
	}

	@Override
	protected Input doInBackground(String... params) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		ResponseEntity<Input> response = rest.postForEntity(params[URL_PARAM], postObject, inputClass);
		validateResponseCode(response.getStatusCode());
		return response.getBody();
	}

	@Override
	protected void validateResponseCode(final HttpStatus status) {
		if (status != HttpStatus.CREATED) {
			cancel(new WebserviceException("Wrong Response: " + status));
		}
	}
}