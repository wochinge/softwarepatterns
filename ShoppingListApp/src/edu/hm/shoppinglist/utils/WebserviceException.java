package edu.hm.shoppinglist.utils;

/**
 * Exception, die im Rahmen von Aufrufen des Webservices
 * auftreten kann.
 * Moegliche Ursachen können beispielsweise Fehler beim
 * Lesen / Schreiben sein oder Serverprobleme.
 *
 * @author Tobias Wochinger
 */
public class WebserviceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Einfacher Konstruktor mit Message.
	 * @param message Exception-Nachricht
	 */
	public WebserviceException(String message) {
		super(message);
	}

	/**
	 * Ermoeglicht es andere Exceptions in eine
	 * Webservice-Exception zu wrappen.
	 *
	 * @param message Zusaetzliche Exception-Nachricht
	 * @param throwable gewrappte Exception
	 */
	public WebserviceException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
