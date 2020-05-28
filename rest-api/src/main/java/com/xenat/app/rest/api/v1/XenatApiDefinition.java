package com.xenat.app.rest.api.v1;

import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.Info;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;

/**
 * XENAT API definition.
 */
@SwaggerDefinition(
		info = @Info(description = "XENAT Rest API", title = "XENAT API", version = "1.0"),
		securityDefinition = @SecurityDefinition(
				apiKeyAuthDefinitions = {
						@ApiKeyAuthDefinition(description = "Context UserId", key = "ctx", name = "CTX-USERID", in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER)
				}
		)

)

public interface XenatApiDefinition {
}
