package de.consol.dus.color.boundary.exceptions.mapper;

import de.consol.dus.color.boundary.exceptions.ColorWithHexCodeAlreadyExistsException;
import de.consol.dus.color.boundary.transfer.response.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ColorWithHexCodeAlreadyExistsExceptionMapper
    implements ExceptionMapper<ColorWithHexCodeAlreadyExistsException> {
  @Override
  public Response toResponse(ColorWithHexCodeAlreadyExistsException exception) {
    return Response.status(Response.Status.NOT_FOUND).entity(ErrorResponse.of(exception)).build();
  }
}
