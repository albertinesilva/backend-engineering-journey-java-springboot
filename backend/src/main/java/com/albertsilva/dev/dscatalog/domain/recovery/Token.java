package com.albertsilva.dev.dscatalog.domain.recovery;

import java.time.Instant;

import com.albertsilva.dev.dscatalog.domain.user.User;
import com.albertsilva.dev.dscatalog.dto.token.request.TokenCreateRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_token")
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String token;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private Instant expireDate;

  @Column(nullable = false)
  private Boolean used;

  protected Token() {
  }

  public Token(TokenCreateRequest request, User user) {
    this.token = request.token();
    this.user = user;
    this.expireDate = request.expireDate();
    this.used = false;
  }

  public void markAsUsed() {
    this.used = true;
  }
}