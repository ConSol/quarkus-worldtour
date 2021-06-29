package de.consol.dus.user.boundary.transfer.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
@AllArgsConstructor
public class UserResponse {
  String username;
  LocalDate birthDate;
  ColorResponse favoriteColor;
}
