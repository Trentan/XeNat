package com.xenat.app.rest.servlet;

import com.github.bordertech.swagger.servlet.SwaggerUiLaunchServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Client launch swagger servlet.
 */
@WebServlet(urlPatterns = "/launchswagger")
public class XenatLaunchSwaggerServlet extends SwaggerUiLaunchServlet {
}
