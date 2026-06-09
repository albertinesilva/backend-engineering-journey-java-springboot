package com.albertsilva.dev.dscatalog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.albertsilva.dev.dscatalog.domain.recovery.Token;
import com.albertsilva.dev.dscatalog.domain.recovery.enums.TokenType;
import com.albertsilva.dev.dscatalog.domain.user.User;
import com.albertsilva.dev.dscatalog.repository.TokenRepository;
import com.albertsilva.dev.dscatalog.service.exception.InvalidTokenException;

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
    return tokenRepository.findByToken(value)
        .orElseThrow(() -> new InvalidTokenException("Token inválido ou expirado"));
  }

  public void disableAllActivationTokens(User user) {

    List<Token> tokens = tokenRepository.findByUserAndTypeAndDisabledFalse(user, TokenType.ACTIVATION);

    tokens.forEach(Token::disable);
  }

  public void disableAllPasswordRecoveryTokens(User user) {

    List<Token> tokens = tokenRepository.findByUserAndTypeAndDisabledFalse(user, TokenType.PASSWORD_RECOVERY);

    tokens.forEach(Token::disable);
  }
}
