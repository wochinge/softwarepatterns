package edu.hm.shoppinglist.web.requests;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import edu.hm.shoppinglist.callback.OnFailListener;
import edu.hm.shoppinglist.web.WebTask;

/**
 * Klasse für alle Post-Webaufrufe
 * @author Tobias Wochinger
 *
 */
public class Put<Input> extends WebTask<String, Void, Void> {

	private Input putObject;

	public Put(OnFailListener failListener) {
		super(null, failListener);
	}

	public void setPutObject(Input postObject) {
		this.putObject = postObject;
	}

	@Override
	protected Void doInBackground(String... params) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		rest.put(params[URL_PARAM], putObject);

		return null;
	}
}