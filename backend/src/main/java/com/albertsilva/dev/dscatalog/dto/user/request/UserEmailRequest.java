package com.albertsilva.dev.dscatalog.dto.user.request;

import com.albertsilva.dev.dscatalog.validation.user.annotation.ValidEmail;

import jakarta.validation.constraints.NotBlank;

public record UserEmailRequest(

  @NotBlank(message = "{user.email.notBlank}") 
  @ValidEmail 
  String email) {
}
