package de.consol.dus.color.boundary.transfer.messaging.outgoing;

import lombok.Value;

@Value
public class NewColorCreatedMessage {
  String name;

  private NewColorCreatedMessage(String name) {
    this.name = name;
  }

  public static NewColorCreatedMessage of(String name) {
    return new NewColorCreatedMessage(name);
  }
}
