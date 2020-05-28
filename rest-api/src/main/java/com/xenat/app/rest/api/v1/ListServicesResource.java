package com.xenat.app.rest.api.v1;

import com.github.bordertech.restfriends.envelope.impl.ErrorResponse;
import com.github.bordertech.restfriends.exception.RestBusinessException;
import com.xenat.app.service.list.ListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Xenat REST Resource call a backing service implementation.
 */
@Api(value = "ListServices")
@Path(value = "v1")
@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid request", response = ErrorResponse.class),
	@ApiResponse(code = 500, message = "Internal error", response = ErrorResponse.class)
})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("NoWhitespaceBefore")
public interface ListServicesResource {

	/**
	 * Retrieve table names.
	 *
	 * @return the table names
	 * @throws RestBusinessException a business exception
	 */
	@GET
	@Path("list")
	@ApiOperation(value = "Retrieve module details")
	ListResponse retrieveListDetails(@ApiParam(value = "Module name") @QueryParam("module") String module,
	                                 @ApiParam(value = "Database ID") @QueryParam("dbid") String dbid) throws RestBusinessException;

}
