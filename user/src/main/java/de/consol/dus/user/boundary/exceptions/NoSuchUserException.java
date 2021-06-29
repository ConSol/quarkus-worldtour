package de.consol.dus.user.boundary.exceptions;

import java.util.NoSuchElementException;

public class NoSuchUserException extends NoSuchElementException {
  private static final String MESSAGE_FORMAT = "No user with username [%s] found.";

  private NoSuchUserException(String username) {
    super(String.format(MESSAGE_FORMAT, username));
  }

  public static NoSuchUserException of(String username) {
    return new NoSuchUserException(username);
  }
}
