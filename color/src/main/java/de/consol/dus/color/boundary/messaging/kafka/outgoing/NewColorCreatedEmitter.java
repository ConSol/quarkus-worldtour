package de.consol.dus.color.boundary.messaging.kafka.outgoing;

import de.consol.dus.color.boundary.transfer.messaging.outgoing.NewColorCreatedMessage;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class NewColorCreatedEmitter {
  private static final Logger LOGGER =LoggerFactory.getLogger(NewColorCreatedEmitter.class);

  private final Emitter<NewColorCreatedMessage> emitter;

  public NewColorCreatedEmitter(
      @Channel("kafka-new-color-created-channel") Emitter<NewColorCreatedMessage> emitter) {
    this.emitter = emitter;
  }

  public void emit(String newColorName) {
    final NewColorCreatedMessage newColor = NewColorCreatedMessage.of(newColorName);
    LOGGER.info("Sending newly created color: {}", newColor);
    emitter.send(newColor);
  }
}
