package com.albertsilva.dev.dscatalog.dto.email.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResendActivationRequest(
  @Email 
  @NotBlank 
  String email) {
}
