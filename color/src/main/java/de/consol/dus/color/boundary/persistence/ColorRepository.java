package de.consol.dus.color.boundary.persistence;

import de.consol.dus.color.entity.Color;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
  Optional<Color> findByName(String name);

  Optional<Color> findByHexCode(String hexCode);
}
