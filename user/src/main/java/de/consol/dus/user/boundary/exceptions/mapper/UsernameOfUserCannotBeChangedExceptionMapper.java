package de.consol.dus.user.boundary.exceptions.mapper;

import de.consol.dus.user.boundary.exceptions.UsernameOfUserCannotBeChangedException;
import de.consol.dus.user.boundary.transfer.response.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UsernameOfUserCannotBeChangedExceptionMapper
    implements ExceptionMapper<UsernameOfUserCannotBeChangedException> {
  @Override
  public Response toResponse(UsernameOfUserCannotBeChangedException exception) {
    return Response.status(Response.Status.CONFLICT).entity(ErrorResponse.of(exception)).build();
  }
}
