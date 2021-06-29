package de.consol.dus.color.entity.mapper;

import de.consol.dus.color.boundary.transfer.request.CreateNewColorRequest;
import de.consol.dus.color.boundary.transfer.response.ColorResponse;
import de.consol.dus.color.entity.Color;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ColorMapper {
  Color requestToEntity(CreateNewColorRequest request);

  ColorResponse entityToResponse(Color color);
}
