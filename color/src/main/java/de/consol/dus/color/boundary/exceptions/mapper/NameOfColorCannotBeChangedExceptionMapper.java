package de.consol.dus.color.boundary.exceptions.mapper;

import de.consol.dus.color.boundary.exceptions.NameOfColorCannotBeChangedException;
import de.consol.dus.color.boundary.transfer.response.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NameOfColorCannotBeChangedExceptionMapper
    implements ExceptionMapper<NameOfColorCannotBeChangedException> {
  @Override
  public Response toResponse(NameOfColorCannotBeChangedException exception) {
    return Response.status(Response.Status.CONFLICT).entity(ErrorResponse.of(exception)).build();
  }
}
