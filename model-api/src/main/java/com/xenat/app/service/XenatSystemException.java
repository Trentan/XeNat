package com.xenat.app.service;

import com.github.bordertech.restfriends.exception.RestSystemException;

/**
 * Xenat System Exception.
 */
public class XenatSystemException extends RestSystemException {

	/**
	 * @param message the exception message
	 */
	public XenatSystemException(final String message) {
		super(message);
	}

	/**
	 * @param message the exception message
	 * @param cause the original cause
	 */
	public XenatSystemException(final String message, final Exception cause) {
		super(message, cause);
	}

}
