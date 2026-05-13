package com.albertsilva.dev.dscatalog.dto.user.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(
        @NotBlank(message = "Campo obrigatório")
        String firstName,
        String lastName,

        @Email(message = "Favor informar um email válido")
        String email,
        String password,
        Set<Long> roleIds) {

}
