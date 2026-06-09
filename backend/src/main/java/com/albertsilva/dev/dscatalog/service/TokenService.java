package com.albertsilva.dev.dscatalog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.albertsilva.dev.dscatalog.domain.recovery.Token;
import com.albertsilva.dev.dscatalog.domain.user.User;
import com.albertsilva.dev.dscatalog.repository.TokenRepository;
import com.albertsilva.dev.dscatalog.service.exception.ResourceNotFoundException;

@Service
@Transactional
public class TokenService {

  private final TokenRepository tokenRepository;

  public TokenService(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  public Token createActivationToken(User user) {
    return tokenRepository.save(Token.activationToken(user));
  }

  public Token createPasswordRecoveryToken(User user) {
    return tokenRepository.save(Token.passwordRecoveryToken(user));
  }

  /**
   * Busca token pelo valor.
   */
  @Transactional(readOnly = true)
  public Token findByValue(String value) {
    return tokenRepository.findByToken(value).orElseThrow(() -> new ResourceNotFoundException("Token não encontrado"));
  }

  /**
   * Verifica se o token é válido.
   */
  public boolean isValid(Token token) {
    return token.isValid();
  }

  /**
   * Invalida token.
   */
  public void disable(Token token) {
    token.disable();
    // tokenRepository.save(token);
  }

}
