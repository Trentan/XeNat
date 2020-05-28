package com.xenat.app.rest.servlet;

import com.github.bordertech.swagger.servlet.SwaggerJersey2Servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Client swagger jersey servlet.
 */
@WebServlet(urlPatterns = "/api/*",
		initParams
		= {
			// Tell Jersey to use JSON
			@WebInitParam(name = "com.sun.jersey.api.json.POJOMappingFeature", value = "true")
			,
			// Tell Jersey the Application
			@WebInitParam(name = "javax.ws.rs.Application", value = "com.xenat.app.rest.servlet.XenatRestApplication")
		},
		loadOnStartup = 1
)
@SuppressWarnings("NoWhitespaceBefore")
public class XenatRestServlet extends SwaggerJersey2Servlet {
}
