package com.xenat.app.ui.common;

import com.github.bordertech.wcomponents.Margin;
import com.github.bordertech.wcomponents.Size;

/**
 * Holds the constants used by the UI.
 */
public final class Constants {

	/**
	 * Prevent instantiation of this utility class.
	 */
	private Constants() {
		// Do nothing
	}

	/**
	 * Default GAP.
	 */
	public static final int GAP = 6;

	/**
	 * Default Large GAP.
	 */
	public static final int GAP_LARGE = 12;

	/**
	 * Default Half (50).
	 */
	public static final int HALF = 50;

	/**
	 * North margin.
	 */
	public static final Margin NORTH_MARGIN = new Margin(Size.MEDIUM, Size.ZERO, Size.ZERO, Size.ZERO);

	/**
	 * North margin large.
	 */
	public static final Margin NORTH_MARGIN_LARGE = new Margin(Size.LARGE, Size.ZERO, Size.ZERO, Size.ZERO);

	/**
	 * South margin.
	 */
	public static final Margin SOUTH_MARGIN = new Margin(Size.ZERO, Size.ZERO, Size.MEDIUM, Size.ZERO);

	/**
	 * South margin large.
	 */
	public static final Margin SOUTH_MARGIN_LARGE = new Margin(Size.ZERO, Size.ZERO, Size.LARGE, Size.ZERO);

}
