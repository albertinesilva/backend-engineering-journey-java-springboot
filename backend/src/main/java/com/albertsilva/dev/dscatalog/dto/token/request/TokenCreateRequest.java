package com.albertsilva.dev.dscatalog.dto.token.request;

import java.time.Instant;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO de requisição para criação de um token.
 *
 * <p>
 * Este record encapsula os dados necessários para registrar
 * um token de recuperação de senha associado a um usuário.
 * </p>
 *
 * <ul>
 * <li>{@code token}: valor único do token</li>
 * <li>{@code expireDate}: data e hora de expiração do token</li>
 * </ul>
 *
 * <p>
 * Exemplo:
 * </p>
 *
 * <pre>{@code
 * TokenCreateRequest request = new TokenCreateRequest(
 *     "8c7f5d8e-3e0d-4d8a-9c0a-4b8a3f6f8d9c",
 *     Instant.now().plus(Duration.ofHours(1)));
 * }</pre>
 *
 * @param token valor único do token
 * @param userId ID do usuário associado ao token
 * @param expireDate data e hora de expiração do token
 */
public record TokenCreateRequest(

  @NotBlank(message = "{token.notBlank}")
  String token,

  @NotNull(message = "{token.userId.notNull}")
  Long userId,

  @NotNull(message = "{token.expireDate.notNull}")
  @Future(message = "{token.expireDate.future}")
  Instant expireDate) {
    
}
