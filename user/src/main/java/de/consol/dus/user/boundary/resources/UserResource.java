package de.consol.dus.user.boundary.resources;

import de.consol.dus.user.UserService;
import de.consol.dus.user.boundary.exceptions.NoSuchUserException;
import de.consol.dus.user.boundary.exceptions.UserWithUsernameAlreadyExistsException;
import de.consol.dus.user.boundary.transfer.request.CreateNewUserRequest;
import de.consol.dus.user.boundary.transfer.request.UpdateUserRequest;
import io.quarkus.security.Authenticated;
import java.net.URI;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.yaml.snakeyaml.util.UriEncoder;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class UserResource {
  private static final String CREATED_URL_TEMPLATE = "/api/users/%s";

  private final UserService userService;

  @GET
  @Authenticated
  public Response findAllUsers() {
    return Response.ok(userService.findAll()).build();
  }

  @GET
  @Path("{username}")
  @Authenticated
  public Response findUserByUsername(@PathParam("username") String username) {
    return Response
        .ok(userService.findByUsername(username)
            .orElseThrow(() -> NoSuchUserException.of(username)))
        .build();
  }

  @POST
  @RolesAllowed("admin")
  public Response createNewUser(CreateNewUserRequest request) {
    final String username = request.getUsername();
    if (userService.findByUsername(username).isPresent()) {
      throw UserWithUsernameAlreadyExistsException.of(username);
    }
    return Response
        .created(URI.create(
            UriEncoder.encode(String.format(CREATED_URL_TEMPLATE, username))))
        .entity(userService.createNewUser(request))
        .build();
  }

  @PUT
  @Path("{username}")
  @RolesAllowed("admin")
  public Response updateUser(
      @PathParam("username") @Size(min = 3, max = 255) String username,
      @Valid UpdateUserRequest request) {
    return Response
        .ok(userService.updateUser(username, request)
            .orElseThrow(() -> NoSuchUserException.of(username)))
        .build();
  }
}
