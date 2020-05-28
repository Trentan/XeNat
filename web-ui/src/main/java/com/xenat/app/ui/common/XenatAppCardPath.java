package com.xenat.app.ui.common;

/**
 * This contains all the request paths.
 *
 * @author Trentan Healey
 * @since 25 May 2020
 */
public enum XenatAppCardPath {
	XENAT_LIST("list");

	/**
	 * request path.
	 */
	private final String path;

	/**
	 * constructor.
	 *
	 * @param path request path
	 */
	XenatAppCardPath(final String path) {
		this.path = path;
	}

	/**
	 * find the XenatAppPath value via url.
	 *
	 * @param url request relative path
	 * @return the matched Xenat app path
	 */
	public static XenatAppCardPath find(final String url) {
		for (XenatAppCardPath appPath : values()) {
			if (appPath.getPath().equals(url)) {
				return appPath;
			}
		}
		return null;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
}
