package com.xenat.app.ui.common;

import com.github.bordertech.wcomponents.WMenuItem;

/**
 * This a customised MenuItem that applies visibility.
 *
 * @author Trentan Healey
 * @since 26 05 2020
 */
public class XenatMenuItem extends WMenuItem {

	private final XenatAppCardPath path;

	/**
	 * @param text the menu text
	 * @param path the path for the menu item
	 */
	public XenatMenuItem(final String text, final XenatAppCardPath path) {
		super(text, path.getPath());
		this.path = path;
	}

	/**
	 * @return the app path for this menu item
	 */
	public XenatAppCardPath getPath() {
		return path;
	}

}
