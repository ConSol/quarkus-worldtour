package de.consol.dus.color.boundary.transfer.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class UpdateColorRequest {
  @NotNull
  @Size(min = 3, max = 255)
  String name;

  @Pattern(regexp = "[0-9a-f]{6}")
  String hexCode;
}
