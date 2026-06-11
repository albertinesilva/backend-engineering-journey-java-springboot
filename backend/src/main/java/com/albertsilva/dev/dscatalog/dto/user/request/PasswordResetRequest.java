package com.albertsilva.dev.dscatalog.dto.user.request;

import com.albertsilva.dev.dscatalog.validation.user.annotation.StrongPassword;

import jakarta.validation.constraints.NotBlank;

public record PasswordResetRequest(

  @NotBlank(message = "O token de redefinição de senha é obrigatório")
  String token,

  @NotBlank(message = "A nova senha é obrigatória")
  @StrongPassword
  String password) {

}
