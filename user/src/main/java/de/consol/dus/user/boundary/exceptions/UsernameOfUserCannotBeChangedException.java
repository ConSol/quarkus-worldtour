package de.consol.dus.user.boundary.exceptions;

public class UsernameOfUserCannotBeChangedException extends UnsupportedOperationException {
  private static final String MESSAGE_TEMPLATE = "username of user [%s] cannot be changed to [%s]";

  private UsernameOfUserCannotBeChangedException(String currentUsername, String newUsername) {
    super(String.format(MESSAGE_TEMPLATE, currentUsername, newUsername));
  }

  public static UsernameOfUserCannotBeChangedException of(
      String currentUsername,
      String newUsername) {
    return new UsernameOfUserCannotBeChangedException(currentUsername, newUsername);
  }
}
