package edu.hm.shoppinglist.web;


import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;

import android.content.Context;
import android.os.AsyncTask;
import edu.hm.shoppinglist.callback.OnFailListener;
import edu.hm.shoppinglist.callback.OnResultListener;

/**
 * Abstrakte Superklasse für alle Webtasks (GET, PUT, POST, DELETE ...)
 *
 * @author Tobias Wochinger
 *
 * @param <Params> Startparameter. In der Regel ein String.
 * @param <Progress>  Datentyp um Fortschritt anzuzeigen.
 */
public abstract class WebTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

	protected Context context;
	protected URL url;
	protected HttpURLConnection connection;

	protected OnFailListener failListener;
	protected OnResultListener<Result> resultListener;
	protected Exception exception;

	public final static String CONTENT_TYPE = "Content-Type";
	public final static String JSON = "application/json";
	protected final static int URL_PARAM = 0;

	public WebTask(OnResultListener<Result> resultListener,
			OnFailListener failListener) {
		this.failListener = failListener;
		this.resultListener = resultListener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Result result) {
		if (resultListener != null) {
			resultListener.onResult(result);
		}
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		exception.printStackTrace();
		if (failListener != null) {
			failListener.onFail(exception);
		}
	}

	protected void validateResponseCode(final HttpStatus status) {

	}


	protected void cancel(Exception e) {
		this.exception = e;
		cancel(true);
	}
}