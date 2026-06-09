package com.albertsilva.dev.dscatalog.web.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
@RequestMapping("/api/v1/account")
public class AccountController {

  private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

  private final AccountService accountServiceService;

  /**
   * Construtor para injeção de dependências.
   *
   * @param userService serviço de usuário
   */
  public AccountController(AccountService accountServiceService) {
    this.accountServiceService = accountServiceService;
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

    logger.debug("Recebendo requisição para criar usuário: {}", userRegisterRequest);
    UserResponse response = accountServiceService.register(userRegisterRequest);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();

    logger.info("Usuário criado com sucesso. id: {}", response.id());
    return ResponseEntity.created(uri).body(response);
  }

  /**
   * Envia um email de confirmação para o usuário.
   */
  @PostMapping("/confirm-email")
  public void confirmEmail() {
  }

  /**
   * Ativa uma conta de usuário.
   */
  public void activate() {

  }

  /**
   * Desativa uma conta de usuário.
   */
  public void deactivate() {

  }

  /**
   * Solicita a recuperação de senha.
   */
  public void recoverPassword() {

  }

  /**
   * Redefine a senha utilizando um token válido.
   */
  public void resetPassword() {

  }

}
