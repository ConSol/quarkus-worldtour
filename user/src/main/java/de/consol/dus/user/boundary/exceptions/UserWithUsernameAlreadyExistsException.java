package de.consol.dus.user.boundary.exceptions;

public class UserWithUsernameAlreadyExistsException extends IllegalStateException {
  private static final String MESSAGE_TEMPLATE = "User with username [%s] already exists";

  private UserWithUsernameAlreadyExistsException(String username) {
    super(String.format(MESSAGE_TEMPLATE, username));
  }

  public static UserWithUsernameAlreadyExistsException of(String username) {
    return new UserWithUsernameAlreadyExistsException(username);
  }
}
