package com.albertsilva.dev.dscatalog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertsilva.dev.dscatalog.domain.recovery.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

  /**
   * Busca um token pelo seu valor.
   *
   * @param token valor do token a ser buscado
   * @return o token encontrado ou {@code null} se não existir
   */
  Optional<Token> findByToken(String token);
  
}
