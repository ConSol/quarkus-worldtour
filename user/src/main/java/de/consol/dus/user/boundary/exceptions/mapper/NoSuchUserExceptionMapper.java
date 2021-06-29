package de.consol.dus.user.boundary.exceptions.mapper;

import de.consol.dus.user.boundary.exceptions.NoSuchUserException;
import de.consol.dus.user.boundary.transfer.response.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoSuchUserExceptionMapper implements ExceptionMapper<NoSuchUserException> {
  @Override
  public Response toResponse(NoSuchUserException exception) {
    return Response.status(Response.Status.NOT_FOUND).entity(ErrorResponse.of(exception)).build();
  }
}
