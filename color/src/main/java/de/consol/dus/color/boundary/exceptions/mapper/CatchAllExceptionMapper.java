package de.consol.dus.color.boundary.exceptions.mapper;

import de.consol.dus.color.boundary.transfer.response.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class CatchAllExceptionMapper implements ExceptionMapper<Exception> {
  private static final Logger LOGGER = Logger.getLogger(CatchAllExceptionMapper.class);

  @Override
  public Response toResponse(Exception exception) {
    LOGGER.error("Exception thrown", exception);
    return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(ErrorResponse.of("Internal Server Error"))
        .build();
  }
}
