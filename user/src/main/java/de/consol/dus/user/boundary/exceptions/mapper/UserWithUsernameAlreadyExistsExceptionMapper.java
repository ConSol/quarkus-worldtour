package de.consol.dus.user.boundary.exceptions.mapper;

import de.consol.dus.user.boundary.exceptions.UserWithUsernameAlreadyExistsException;
import de.consol.dus.user.boundary.transfer.response.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserWithUsernameAlreadyExistsExceptionMapper
    implements ExceptionMapper<UserWithUsernameAlreadyExistsException> {
  @Override
  public Response toResponse(UserWithUsernameAlreadyExistsException exception) {
    return Response.status(Response.Status.CONFLICT).entity(ErrorResponse.of(exception)).build();
  }
}
