package com.xenat.app.service;

/**
 * Service response with messages.
 */
public abstract class AbstractServiceMessagesResponse implements ServiceMessagesResponse {

	private MessagesMeta meta;

	/**
	 * Default constructor.
	 */
	public AbstractServiceMessagesResponse() {
		// Do nothing
	}

	/**
	 * @param meta the messages meta
	 */
	public AbstractServiceMessagesResponse(final MessagesMeta meta) {
		this.meta = meta;
	}

	@Override
	public MessagesMeta getMeta() {
		return meta;
	}

	@Override
	public void setMeta(final MessagesMeta meta) {
		this.meta = meta;
	}

}
