package com.albertsilva.dev.dscatalog.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

  /**
   * Registra uma nova conta de usuário.
   */
  public void register() {

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
