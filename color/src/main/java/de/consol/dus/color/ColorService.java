package de.consol.dus.color;

import de.consol.dus.color.boundary.exceptions.NameOfColorCannotBeChangedException;
import de.consol.dus.color.boundary.persistence.ColorRepository;
import de.consol.dus.color.boundary.transfer.request.CreateNewColorRequest;
import de.consol.dus.color.boundary.transfer.request.UpdateColorRequest;
import de.consol.dus.color.boundary.transfer.response.ColorResponse;
import de.consol.dus.color.entity.Color;
import de.consol.dus.color.entity.mapper.ColorMapper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ColorService {
  private final ColorMapper colorMapper;
  private final ColorRepository colorRepository;

  public List<ColorResponse> findAllColors() {
    return colorRepository.findAll().stream()
        .map(colorMapper::entityToResponse)
        .collect(Collectors.toList());
  }

  public Optional<ColorResponse> findByName(String name) {
    return Optional.of(name)
        .flatMap(colorRepository::findByName)
        .map(colorMapper::entityToResponse);
  }

  public ColorResponse createNewColor(CreateNewColorRequest request) {
    final Color color = colorMapper.requestToEntity(request);
    final Color saved = colorRepository.save(color);
    return colorMapper.entityToResponse(saved);
  }

  public Optional<ColorResponse> updateColor(String name, UpdateColorRequest request) {
    final String newName = request.getName();
    if (!Objects.equals(name, newName)) {
      throw NameOfColorCannotBeChangedException.of(name, newName);
    }
    return colorRepository.findByName(name)
        .map(Color::toBuilder)
        .map(builder -> builder.hexCode(request.getHexCode()))
        .map(Color.Builder::build)
        .map(colorRepository::save)
        .map(colorMapper::entityToResponse);
  }

  public Optional<ColorResponse> findByNexCode(String hexCode) {
    return colorRepository.findByHexCode(hexCode)
        .map(colorMapper::entityToResponse);
  }
}
