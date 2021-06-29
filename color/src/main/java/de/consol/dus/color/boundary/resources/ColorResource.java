package de.consol.dus.color.boundary.resources;

import de.consol.dus.color.ColorService;
import de.consol.dus.color.boundary.exceptions.ColorWithHexCodeAlreadyExistsException;
import de.consol.dus.color.boundary.exceptions.ColorWithNameAlreadyExistsException;
import de.consol.dus.color.boundary.exceptions.NoSuchColorException;
import de.consol.dus.color.boundary.transfer.request.CreateNewColorRequest;
import de.consol.dus.color.boundary.transfer.request.UpdateColorRequest;
import java.net.URI;
import java.util.Optional;
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

@Path("/api/colors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class ColorResource {
  private final ColorService colorService;

  @GET
  public Response findAllColors() {
    return Response.ok(colorService.findAllColors()).build();
  }

  @GET
  @Path("{name}")
  public Response findColorByName(@PathParam("name") @Size(min = 3, max = 255) String name) {
    return Response
        .ok(colorService.findByName(name)
            .orElseThrow(() -> NoSuchColorException.of(name)))
        .build();
  }

  @POST
  public Response createNewColor(@Valid CreateNewColorRequest request) {
    final String name = request.getName();
    if (colorService.findByName(name).isPresent()) {
      throw ColorWithNameAlreadyExistsException.of(name);
    }
    final Optional<String> maybeHexCode = Optional.ofNullable(request.getHexCode());
    if (maybeHexCode.isPresent() && colorService.findByNexCode(maybeHexCode.get()).isPresent()) {
      throw ColorWithHexCodeAlreadyExistsException.of(maybeHexCode.get());
    }
    return Response
        .created(URI.create(UriEncoder.encode(name)))
        .entity(colorService.createNewColor(request))
        .build();
  }


  @PUT
  @Path("{name}")
  public Response updateColor(
      @PathParam("name") @Size(min = 3, max = 6) String name,
      @Valid UpdateColorRequest request) {
    return Response
        .ok(colorService.updateColor(name, request)
            .orElseThrow((() -> NoSuchColorException.of(request.getName()))))
        .build();
  }
}
