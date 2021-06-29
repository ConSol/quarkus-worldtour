package de.consol.dus.user.boundary.transfer.request;

import de.consol.dus.user.boundary.transfer.response.ColorResponse;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
@AllArgsConstructor
public class CreateNewUserRequest {
  @NotNull
  @Size(min = 3, max = 255)
  String username;

  @NotNull
  LocalDate birthDate;

  ColorResponse favoriteColor;
}
