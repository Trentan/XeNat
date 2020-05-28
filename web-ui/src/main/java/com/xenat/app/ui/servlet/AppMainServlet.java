package com.xenat.app.ui.servlet;

import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.registry.UIRegistry;
import com.github.bordertech.wcomponents.servlet.WServlet;
import com.xenat.app.ui.application.XenatApp;

import javax.servlet.annotation.WebServlet;

/**
 * App main WEB UI servlet.
 */
@WebServlet(urlPatterns = {
		"/app",
		"/list",
		"/whereIs"})
public class AppMainServlet extends WServlet {

	@Override
	public WComponent getUI(final Object httpServletRequest) {
		return UIRegistry.getInstance().getUI(XenatApp.class.getName());
	}

}
