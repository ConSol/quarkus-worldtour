package de.consol.dus.color.boundary.exceptions;

public class ColorWithHexCodeAlreadyExistsException extends IllegalStateException {
  private static final String MESSAGE_TEMPLATE = "Color with hexCode [%s] already exists";

  private ColorWithHexCodeAlreadyExistsException(String hexCode) {
    super(String.format(MESSAGE_TEMPLATE, hexCode));
  }

  public static ColorWithHexCodeAlreadyExistsException of(String hexCode) {
    return new ColorWithHexCodeAlreadyExistsException(hexCode);
  }
}
