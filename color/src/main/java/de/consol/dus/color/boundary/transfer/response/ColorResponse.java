package de.consol.dus.color.boundary.transfer.response;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
@AllArgsConstructor
public class ColorResponse {
  @NotNull
  @Size(min = 3, max = 255)
  String name;

  @Pattern(regexp = "[0-9a-f]{6}")
  String hexCode;
}
