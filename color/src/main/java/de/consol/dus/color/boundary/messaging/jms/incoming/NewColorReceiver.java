package de.consol.dus.color.boundary.messaging.jms.incoming;

import de.consol.dus.color.ColorService;
import de.consol.dus.color.boundary.transfer.request.CreateNewColorRequest;
import io.smallrye.reactive.messaging.annotations.Blocking;
import java.util.concurrent.CompletionStage;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@RequiredArgsConstructor
public class NewColorReceiver {
  private static final Logger LOGGER = LoggerFactory.getLogger(NewColorReceiver.class);

  private final ColorService colorService;

  @Incoming("jms-new-color-channel")
  @Blocking
  @Transactional
  CompletionStage<Void> receiveColor(Message<CreateNewColorRequest> message) {
    final CreateNewColorRequest request = message.getPayload();
    LOGGER.info("Received color to create: {}", request);
    if (colorService.findByName(request.getName()).isEmpty()) {
      colorService.createNewColor(request);
    }
    return message.ack();
  }
}
