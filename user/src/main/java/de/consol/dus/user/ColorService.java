package de.consol.dus.user;

import de.consol.dus.user.boundary.persistence.ColorRepository;
import de.consol.dus.user.boundary.transfer.response.ColorResponse;
import de.consol.dus.user.entity.Color;
import de.consol.dus.user.entity.mapper.ColorMapper;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ColorService {
  private final ColorRepository colorRepository;
  private final ColorMapper colorMapper;

  @Transactional
  public ColorResponse createOrFetchColorByName(String name) {
    final Optional<Color> fetched = colorRepository.findByName(name);
    if (fetched.isPresent()) {
      return colorMapper.entityToResponse(fetched.get());
    } else {
      return colorMapper.entityToResponse(colorRepository.save(Color.builder().name(name).build()));
    }
  }
}
