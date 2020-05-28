package com.xenat.app.client.lde;

import com.github.bordertech.lde.api.LdeLauncher;

/**
 * Start Tomcat Server.
 */
@SuppressWarnings("HideUtilityClassConstructor")
public final class XenatWebLauncherProxy {

	/**
	 * Launch LDE Server.
	 *
	 * @param args the main arguments
	 */
	public static void main(final String[] args) {
		LdeLauncher.launchServer();
	}
}
