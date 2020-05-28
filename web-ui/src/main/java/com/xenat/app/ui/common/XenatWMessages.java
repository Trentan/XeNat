package com.xenat.app.ui.common;

import com.github.bordertech.wcomponents.WMessages;

/**
 * Panel that holds the message container. Allows the messages to be included in AJAX responses.
 */
public class XenatWMessages extends WMessages {

	/**
	 * Construct panel.
	 */
	public XenatWMessages() {
		this(false);
	}

	/**
	 * @param persistent true if messages persistent.
	 */
	public XenatWMessages(final boolean persistent) {
		super(persistent);
		setMargin(Constants.SOUTH_MARGIN_LARGE);
	}

	@Override
	public boolean isVisible() {
		// Always visible
		return true;
	}

	@Override
	public boolean isHidden() {
		// Hide the panel when there are no messages so AJAX spinner is not seen
		return getSuccessMessages().isEmpty() && getInfoMessages().isEmpty() && getWarningMessages().isEmpty()
				&& getErrorMessages().isEmpty() && !getValidationErrors().hasErrors();
	}

}
