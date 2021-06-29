package de.consol.dus.color.boundary.exceptions;

public class ColorWithNameAlreadyExistsException extends IllegalStateException {
  private static final String MESSAGE_TEMPLATE = "Color with name [%s] already exists";

  private ColorWithNameAlreadyExistsException(String name) {
    super(String.format(MESSAGE_TEMPLATE, name));
  }

  public static ColorWithNameAlreadyExistsException of(String name) {
    return new ColorWithNameAlreadyExistsException(name);
  }
}
