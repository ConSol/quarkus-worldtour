package de.consol.dus.color.boundary.exceptions;

import java.util.NoSuchElementException;

public class NoSuchColorException extends NoSuchElementException {
  private static final String MESSAGE_FORMAT = "No color with name [%s] found.";

  private NoSuchColorException(String username) {
    super(String.format(MESSAGE_FORMAT, username));
  }

  public static NoSuchColorException of(String name) {
    return new NoSuchColorException(name);
  }
}
