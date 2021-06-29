package de.consol.dus.color.boundary.transfer.response;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
  @NotNull
  String message;

  public static ErrorResponse of(Exception e) {
    return new ErrorResponse(e.getMessage());
  }

  public static ErrorResponse of(String message) {
    return new ErrorResponse(message);
  }
}
