package de.consol.dus.user.boundary.messaging.kafka.incoming;

import de.consol.dus.user.boundary.transfer.response.ColorResponse;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class ColorResponseDeserializer extends ObjectMapperDeserializer<ColorResponse> {
  public ColorResponseDeserializer() {
    super(ColorResponse.class);
  }
}
