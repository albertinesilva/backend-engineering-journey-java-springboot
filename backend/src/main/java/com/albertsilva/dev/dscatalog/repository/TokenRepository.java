package com.albertsilva.dev.dscatalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertsilva.dev.dscatalog.domain.recovery.Token;
import com.albertsilva.dev.dscatalog.domain.recovery.enums.TokenType;
import com.albertsilva.dev.dscatalog.domain.user.User;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

  /**
   * Busca um token pelo seu valor.
   *
   * @param token valor do token a ser buscado
   * @return o token encontrado ou {@code null} se não existir
   */
  Optional<Token> findByToken(String token);

  /**
   * Busca tokens ativos de um usuário por tipo.
   *
   * @param user o usuário para o qual os tokens devem ser buscados
   * @param type o tipo dos tokens a serem buscados
   * @return uma lista de tokens ativos do usuário para o tipo especificado
   */
  List<Token> findByUserAndTypeAndDisabledFalse(User user, TokenType type);

}
