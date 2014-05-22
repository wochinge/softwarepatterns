package edu.hm.shopping.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class UriUtils {

	public static URI createURI(final URI base, final int newPart) {
		String baseUrl = base.toString();
		String additional = String.valueOf(newPart);
		try {
			return new URI(baseUrl + additional);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Given values are not an URI");
		}
	}
}
