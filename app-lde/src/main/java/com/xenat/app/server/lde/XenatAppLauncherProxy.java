package com.xenat.app.server.lde;

import com.github.bordertech.lde.api.LdeLauncher;

/**
 * Start Tomcat Server.
 */
@SuppressWarnings("HideUtilityClassConstructor")
public final class XenatAppLauncherProxy {

	/**
	 * Launch LDE Server.
	 *
	 * @param args the main arguments
	 */
	public static void main(final String[] args) {
		LdeLauncher.launchServer();
	}
}
