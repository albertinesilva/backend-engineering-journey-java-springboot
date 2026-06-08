package com.albertsilva.dev.dscatalog.dto.email.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO de requisição para criação de um email.
 *
 * <p>
 * Este record encapsula os dados necessários para registrar
 * ou enviar um email através da aplicação.
 * </p>
 *
 * <ul>
 * <li>{@code sender}: endereço de email do remetente</li>
 * <li>{@code recipient}: endereço de email do destinatário</li>
 * <li>{@code subject}: assunto do email</li>
 * <li>{@code content}: conteúdo da mensagem</li>
 * </ul>
 *
 * <p>
 * Exemplo:
 * </p>
 *
 * <pre>{@code
 * EmailCreateRequest request = new EmailCreateRequest(
 *     "noreply@dscatalog.com",
 *     "user@email.com",
 *     "Recuperação de senha",
 *     "Clique no link para redefinir sua senha."
 * );
 * }</pre>
 *
 * @param sender    endereço de email do remetente
 * @param recipient endereço de email do destinatário
 * @param subject   assunto do email
 * @param content   conteúdo da mensagem
 */
public record EmailCreateRequest(

  @NotBlank(message = "{email.sender.notBlank}")
  @Email(message = "{email.sender.invalid}")
  String sender,

  @NotBlank(message = "{email.recipient.notBlank}")
  @Email(message = "{email.recipient.invalid}")
  String recipient,

  @NotBlank(message = "{email.subject.notBlank}")
  @Size(max = 255, message = "{email.subject.size}")
  String subject,

  @NotBlank(message = "{email.content.notBlank}")
  String content) {
    
}
