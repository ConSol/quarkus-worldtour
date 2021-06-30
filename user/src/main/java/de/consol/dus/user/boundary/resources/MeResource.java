package de.consol.dus.user.boundary.resources;

import de.consol.dus.user.UserService;
import de.consol.dus.user.boundary.exceptions.NoSuchUserException;
import io.quarkus.security.Authenticated;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/api/me")
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class MeResource {

  private static final Logger logger = LoggerFactory.getLogger(MeResource.class);

  private final UserService userService;

  @Inject
  JsonWebToken token;

  @GET
  @Authenticated
  public Response me() {
    final String username = token.getName();
    return userService.findByUsername(username)
        .map(Response::ok)
        .map(Response.ResponseBuilder::build)
        .orElseThrow(() -> NoSuchUserException.of(username));
  }
}
