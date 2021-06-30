package de.consol.dus.user.boundary.messaging.jms.outgoing;

import de.consol.dus.user.boundary.transfer.response.ColorResponse;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class NewColorEmitter {
  private static final Logger LOGGER = LoggerFactory.getLogger(NewColorEmitter.class);

  private final Emitter<ColorResponse> emitter;

  public NewColorEmitter(@Channel("jms-new-color-channel") Emitter<ColorResponse> emitter) {
    this.emitter = emitter;
  }

  public ColorResponse emit(ColorResponse response) {
    LOGGER.info("Sending new color to create: {}", response);
    emitter.send(response);
    return response;
  }
}
