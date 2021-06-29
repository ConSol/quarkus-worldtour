package de.consol.dus.user.boundary.persistence;

import de.consol.dus.user.entity.Color;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ColorRepository extends CrudRepository<Color, Long> {
  Optional<Color> findByName(String name);
}
