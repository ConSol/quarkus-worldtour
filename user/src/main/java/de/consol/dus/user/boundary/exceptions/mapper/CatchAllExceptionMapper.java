package de.consol.dus.user.boundary.exceptions.mapper;

import de.consol.dus.user.boundary.transfer.response.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CatchAllExceptionMapper implements ExceptionMapper<Exception> {
  @Override
  public Response toResponse(Exception exception) {
    return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(ErrorResponse.of("Internal Server Error"))
        .build();
  }
}
