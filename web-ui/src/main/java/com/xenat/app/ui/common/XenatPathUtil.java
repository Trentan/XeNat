package com.xenat.app.ui.common;

import com.github.bordertech.wcomponents.Environment;
import com.github.bordertech.wcomponents.UIContext;
import com.github.bordertech.wcomponents.UIContextDelegate;
import com.github.bordertech.wcomponents.UIContextHolder;
import com.github.bordertech.wcomponents.WComponent;
import com.xenat.app.ui.application.XenatApp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a helper class that checks the URL and view access.
 */
public class XenatPathUtil {

	/**
	 * The logger instance for this class.
	 */
	private static final Log LOG = LogFactory.getLog(XenatPathUtil.class);

	private XenatPathUtil() {
	}

	/**
	 * Check if the path url and view match and user has access to view.
	 *
	 * @param mgr the Xenat card manager
	 * @param app the Xenat app
	 */
	public static void checkPathAndAccess(final XenatWCardManager mgr, final XenatApp app) {

		checkViewUrlPath(mgr);

		// Get environment details
		UIContext uic = UIContextHolder.getCurrent();
		uic = UIContextDelegate.getPrimaryUIContext(uic);
		final Environment env = uic.getEnvironment();

		// Add an action to run at the end of all the actions to set the post path to reflect the active card.
		// We need to run this last incase any other actions change the active card.
		uic.invokeLater(() -> {
			String currentPath = env.getPostPath();
			String currentScreenPath = currentPath.substring(currentPath.lastIndexOf('/') + 1);
			XenatAppCardPath destinationPath = mgr.getCurrentPath();
			if (destinationPath != null
					&& destinationPath.getPath() != null
					&& !com.github.bordertech.wcomponents.util.Util.equals(currentScreenPath, destinationPath.getPath())) {
				app.forward(destinationPath.getPath());
			}
		});

	}

	/**
	 * Check the visible card and the URL path match.
	 *
	 * @param mgr the Xenat card manager
	 */
	private static void checkViewUrlPath(final XenatWCardManager mgr) {

		// Get environment details
		UIContext uic = UIContextHolder.getCurrent();
		uic = UIContextDelegate.getPrimaryUIContext(uic);
		final Environment env = uic.getEnvironment();

		// Make sure that the correct card is visible based on the URL.
		String path = env.getPostPath();
		String screenPath = path.substring(path.lastIndexOf('/') + 1);
		// Match path to XenatPath
		XenatAppCardPath appPath = XenatAppCardPath.find(screenPath);
		if (appPath != null) {
			WComponent screen = mgr.getCardForPath(appPath);
			if (screen != null && mgr.getVisible() != screen) {
				mgr.makeVisible(screen);
			}
		}
	}
}
