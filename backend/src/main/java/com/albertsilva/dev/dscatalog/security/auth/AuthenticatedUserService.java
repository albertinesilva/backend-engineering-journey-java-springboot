package com.albertsilva.dev.dscatalog.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.albertsilva.dev.dscatalog.domain.user.User;
import com.albertsilva.dev.dscatalog.repository.UserRepository;
import com.albertsilva.dev.dscatalog.service.exception.AuthenticatedUserNotFoundException;

@Service
public class AuthenticatedUserService {

  private final UserRepository userRepository;

  public AuthenticatedUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getAuthenticatedUser() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
      throw new AuthenticatedUserNotFoundException("Authentication not found");
    }

    if (!(authentication.getPrincipal() instanceof Jwt)) {
      throw new AuthenticatedUserNotFoundException("Invalid authentication principal");
    }

    Jwt jwt = (Jwt) authentication.getPrincipal();

    String username = jwt.getClaimAsString("username");

    if (username == null || username.isBlank()) {
      throw new AuthenticatedUserNotFoundException("Username claim not found");
    }

    return userRepository.findByEmail(username)
        .orElseThrow(() -> new AuthenticatedUserNotFoundException("Authenticated user not found"));
  }
}
