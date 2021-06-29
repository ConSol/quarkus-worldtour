package de.consol.dus.user.entity.mapper;

import de.consol.dus.user.boundary.transfer.response.ColorResponse;
import de.consol.dus.user.entity.Color;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ColorMapper {
  Color colorResponseToColor(ColorResponse response);

  ColorResponse entityToResponse(Color color);
}
