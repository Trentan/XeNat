package com.xenat.app.service;

import com.github.bordertech.restfriends.exception.RestSystemException;

/**
 * Xenat Business Exception.
 */
public class XenatUnauthorisedException extends RestSystemException {
	/**
	 * @param message the exception message
	 */
	public XenatUnauthorisedException(final String message) {
		super(message);
	}

	/**
	 * @param message the exception message
	 * @param cause the original cause
	 */
	public XenatUnauthorisedException(final String message, final Exception cause) {
		super(message, cause);
	}

	@Override
	public int getStatusCode()
	{
		return 401;
	}

}
