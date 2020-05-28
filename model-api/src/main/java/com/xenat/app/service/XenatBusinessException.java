package com.xenat.app.service;

import com.github.bordertech.restfriends.exception.RestBusinessException;

/**
 * Xenat Business Exception.
 */
public class XenatBusinessException extends RestBusinessException {

	protected XenatBusinessException() {
		super(null);
	}

	/**
	 * @param message the exception message
	 */
	public XenatBusinessException(final String message) {
		super(message);
	}

	/**
	 * @param message the exception message
	 * @param cause the original cause
	 */
	public XenatBusinessException(final String message, final Exception cause) {
		super(message, cause);
	}

}
