package com.albertsilva.dev.dscatalog.service;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.albertsilva.dev.dscatalog.domain.recovery.Token;
import com.albertsilva.dev.dscatalog.domain.user.Role;
import com.albertsilva.dev.dscatalog.domain.user.User;
import com.albertsilva.dev.dscatalog.dto.user.request.UserRegisterRequest;
import com.albertsilva.dev.dscatalog.dto.user.response.UserResponse;
import com.albertsilva.dev.dscatalog.mapper.user.UserMapper;
import com.albertsilva.dev.dscatalog.repository.RoleRepository;
import com.albertsilva.dev.dscatalog.repository.UserRepository;
import com.albertsilva.dev.dscatalog.service.exception.InvalidTokenException;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

@Service
public class AccountService {

  private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;
  private final EmailService emailService;

  public AccountService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper,
      PasswordEncoder passwordEncoder, TokenService tokenService, EmailService emailService) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
    this.tokenService = tokenService;
    this.emailService = emailService;
  }

  @Transactional
  public UserResponse register(UserRegisterRequest request) throws MessagingException {

    logger.debug("Registrando novo usuário. email: {}", request.email());

    Role operator = roleRepository.findByAuthority("ROLE_OPERATOR");
    User user = userMapper.toEntity(request, Set.of(operator));
    user.setPassword(passwordEncoder.encode(request.password()));
    user.deactivate();

    user = userRepository.save(user);

    Token activationToken = tokenService.createActivationToken(user);

    emailService.sendActivationEmailAsync(user.getFirstName(), user.getEmail(), activationToken.getToken());

    logger.info("Usuário registrado com sucesso. id: {}", user.getId());

    return userMapper.toResponse(user);
  }

  @Transactional
  public void confirmEmail(String tokenValue) {

    Token token = tokenService.findByValue(tokenValue);

    if (!tokenService.isValid(token)) {
      throw new InvalidTokenException("Token inválido ou expirado");
    }

    User user = token.getUser();

    user.activate();

    token.disable();
  }

  @Transactional
  public void requestPasswordRecovery(String email) {

    Optional<User> user = userRepository.findByEmail(email);

    if (user.isEmpty()) {
      throw new IllegalArgumentException("Usuário não encontrado");
    }

    Token token = tokenService.createPasswordRecoveryToken(user.get());

    emailService.sendRecoveryEmail(user, token.getToken());
  }
}
