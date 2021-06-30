package de.consol.dus.user.boundary.messaging.kafka.incoming;

import de.consol.dus.user.ColorService;
import de.consol.dus.user.boundary.transfer.response.ColorResponse;
import io.smallrye.reactive.messaging.annotations.Blocking;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@RequiredArgsConstructor
public class ColorResponseReceiver {
  private static final Logger LOGGER = LoggerFactory.getLogger(ColorResponseReceiver.class);

  private final ColorService colorService;

  @Incoming("kafka-new-color-created-channel")
  @Blocking
  @Transactional
  void receiveGroup(ColorResponse colorResponse) {
    LOGGER.info("Received newly created color: {}", colorResponse);
    try {
      colorService.createOrFetchColorByName(colorResponse.getName());
    } catch (Exception e) {
      LOGGER.error("Error:", e);
    }
  }
}
