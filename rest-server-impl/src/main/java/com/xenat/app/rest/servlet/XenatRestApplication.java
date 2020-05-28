package com.xenat.app.rest.servlet;

import com.github.bordertech.swagger.application.SwaggerRestApplication;
import com.github.bordertech.swagger.jackson.JacksonObjectMapperProvider;
import com.xenat.app.rest.server.v1.ListServicesResourceImpl;

/**
 * ClientREST Swagger/Jersey Application.
 */
public class XenatRestApplication extends SwaggerRestApplication {

	/**
	 * Default constructor.
	 */
	public XenatRestApplication() {
		super(ListServicesResourceImpl.class, JacksonObjectMapperProvider.class);
	}
}
