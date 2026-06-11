package com.albertsilva.dev.dscatalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.albertsilva.dev.dscatalog.dto.user.request.UserEmailRequest;
import com.albertsilva.dev.dscatalog.dto.user.request.PasswordResetRequest;
import com.albertsilva.dev.dscatalog.dto.user.request.UserRegisterRequest;
import com.albertsilva.dev.dscatalog.dto.user.response.UserResponse;
import com.albertsilva.dev.dscatalog.service.AccountService;
import com.albertsilva.dev.dscatalog.web.exception.response.ProblemDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Tag(name = "Conta", description = "Contém todas as operações aos recursos para gerenciamento de contas de usuário")
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

  private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

  private final AccountService accountService;

  /**
   * Construtor para injeção de dependências.
   *
   * @param userService serviço de usuário
   */
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  /**
   * Endpoint para criação de um novo usuário.
   *
   * <p>
   * Recebe um JSON contendo os dados do usuário e retorna o recurso criado.
   * </p>
   *
   * <p>
   * <b>Fluxo:</b>
   * </p>
   * <ol>
   * <li>Recebe o request</li>
   * <li>Delega para o Service</li>
   * <li>Gera a URI do recurso criado</li>
   * <li>Retorna HTTP 201 (Created)</li>
   * </ol>
   *
   * @param userCreateRequest dados do usuário
   * @return usuário criado com status 201 e header Location
   * @throws MessagingException
   */
  @Operation(summary = "Registra um novo usuário", description = "Requisição para registro de novo usuário", security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
      @ApiResponse(responseCode = "400", description = "Dados inválidos ou campos obrigatórios ausentes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetails.class))),
      @ApiResponse(responseCode = "409", description = "Usuário já existente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetails.class)))
  })
  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest)
      throws MessagingException {

    logger.debug("Recebendo requisição de registro. email={}", userRegisterRequest.email());
    UserResponse response = accountService.register(userRegisterRequest);

    logger.info("Usuário criado com sucesso. id: {}", response.id());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  /**
   * Endpoint para ativação de conta de usuário.
   *
   * <p>
   * Recebe um token de ativação e ativa a conta associada a esse token.
   * </p>
   *
   * <p>
   * <b>Fluxo:</b>
   * </p>
   * <ol>
   * <li>Recebe o token como parâmetro de query</li>
   * <li>Delega para o Service</li>
   * <li>Retorna HTTP 204 (No Content) em caso de sucesso</li>
   * </ol>
   *
   * @param token token de ativação recebido por e-mail
   * @return resposta sem conteúdo com status 204
   */
  @GetMapping("/activate")
  public ResponseEntity<Void> activateAccount(@RequestParam String token) {
    accountService.confirmEmail(token);
    return ResponseEntity.noContent().build();
  }

  /**
   * Endpoint para reenvio do e-mail de ativação.
   *
   * <p>
   * Recebe um JSON contendo o e-mail do usuário e, se o usuário existir e
   * estiver inativo, um novo e-mail de ativação é enviado.
   * </p>
   *
   * <p>
   * <b>Fluxo:</b>
   * </p>
   * <ol>
   * <li>Recebe o request</li>
   * <li>Delega para o Service</li>
   * <li>Retorna HTTP 204 (No Content) em caso de sucesso</li>
   * </ol>
   *
   * <p>
   * Em caso de falha no envio do e-mail, um erro é registrado em log e uma
   * resposta de erro apropriada é retornada.
   * </p>
   *
   * <p>
   * Se o e-mail fornecido não corresponder a nenhum usuário ou se a conta já
   * estiver ativa, a resposta será HTTP 204 (No Content) para evitar
   * exposição de informações sensíveis.
   * </p>
   *
   * @param request dados contendo o e-mail do usuário
   * @return resposta sem conteúdo com status 204
   */
  @Operation(summary = "Reenviar e-mail de ativação", description = " Gera um novo token de ativação e envia um novo e-mail de confirmação para usuários que aindanão ativaram suas contas.", security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "204", description = "E-mail de ativação reenviado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Dados inválidos ou campos obrigatórios ausentes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetails.class))),
      @ApiResponse(responseCode = "500", description = "Erro interno ao processar a solicitação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetails.class)))
  })
  @PostMapping("/resend-activation")
  public ResponseEntity<Void> resendActivationEmail(@Valid @RequestBody UserEmailRequest request)
      throws MessagingException {

    logger.info("Solicitação de reenvio de ativação recebida. email={}", request.email());
    accountService.resendActivationEmail(request.email());
    return ResponseEntity.noContent().build();
  }

  /**
   * Solicita a recuperação de senha.
   */
  @PostMapping("/password-recovery")
  public ResponseEntity<Void> requestPasswordRecovery(@Valid @RequestBody UserEmailRequest request) {
    logger.info("Solicitação de recuperação de senha recebida. email={}", request.email());

    accountService.requestPasswordRecovery(request.email());

    return ResponseEntity.noContent().build();

  }

  /**
   * Redefine a senha utilizando um token válido.
   */
  @PostMapping("/reset-password")
  public ResponseEntity<Void> resetPassword(@Valid @RequestBody PasswordResetRequest request) {

    logger.info("Solicitação de redefinição de senha recebida");

    accountService.resetPassword(request.token(), request.password());

    return ResponseEntity.noContent().build();
  }

  /**
   * Desativa uma conta de usuário.
   */
  @PostMapping("/deactivate")
  public ResponseEntity<Void> deactivateAccount() {
    logger.info("Solicitação de desativação de conta recebida");

    accountService.deactivateAccount();

    return ResponseEntity.noContent().build();
  }

}
