package de.consol.dus.color.boundary.exceptions;

public class NameOfColorCannotBeChangedException extends UnsupportedOperationException {
  private static final String MESSAGE_TEMPLATE
      = "The name of the color [%s] cannot be updated to [%s]";

  private NameOfColorCannotBeChangedException(String currentName, String newName) {
    super(String.format(MESSAGE_TEMPLATE, currentName, newName));
  }

  public static NameOfColorCannotBeChangedException of(String currentName, String newName) {
    return new NameOfColorCannotBeChangedException(currentName, newName);
  }
}
