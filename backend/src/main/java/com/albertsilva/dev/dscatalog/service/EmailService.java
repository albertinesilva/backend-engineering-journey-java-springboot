package com.albertsilva.dev.dscatalog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.albertsilva.dev.dscatalog.domain.recovery.Email;
import com.albertsilva.dev.dscatalog.domain.user.User;
import com.albertsilva.dev.dscatalog.dto.email.request.EmailRegisterRequest;
import com.albertsilva.dev.dscatalog.repository.EmailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

  @Value("${frontend.url}")
  private String frontendUrl;

  @Value("${backend.url}")
  private String backendUrl;

  private final JavaMailSender emailSender;
  private final SpringTemplateEngine templateEngine;
  private final EmailRepository emailRepository;

  public EmailService(JavaMailSender emailSender, SpringTemplateEngine templateEngine,
      EmailRepository emailRepository) {
    this.emailSender = emailSender;
    this.templateEngine = templateEngine;
    this.emailRepository = emailRepository;
  }

  /**
   * Envia um e-mail de ativação de conta de forma assíncrona.
   *
   * @param name             O nome do destinatário.
   * @param email            O e-mail do destinatário.
   * @param tokenForActivate O token para ativação da conta.
   * @throws MessagingException Se ocorrer um erro ao enviar o e-mail.
   */
  @Async
  public void sendActivationEmailAsync(String name, String email, String tokenForActivate) throws MessagingException {
    sendActivationEmail(name, email, tokenForActivate);

  }

  public void sendRecoveryEmail(Optional<User> user, String token) {
  }

  /**
   * Envia um e-mail de ativação de conta.
   *
   * @param name             O nome do destinatário.
   * @param email            O e-mail do destinatário.
   * @param tokenForActivate O token para ativação da conta.
   * @return O token de ativação enviado.
   * @throws MessagingException Se ocorrer um erro ao enviar o e-mail.
   */
  public void sendActivationEmail(String name, String email, String tokenForActivate) throws MessagingException {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

    Context context = new Context();
    context.setVariable("nome", name);
    context.setVariable("titulo", "Bem-vindo ao Dscatalog, " + name + "!");
    context.setVariable("texto",
        "Estamos felizes em tê-lo(a) conosco. Para começar a usar o Dscatalog, confirme seu cadastro clicando no link abaixo.");
    context.setVariable("linkConfirmacao", backendUrl + "/api/v1/account/activate?token=" + tokenForActivate);

    String htmlBody = templateEngine.process("activate_user_by_email_template", context);
    helper.setTo(email);
    helper.setText(htmlBody, true);
    helper.setSubject("Confirmação de Cadastro");
    helper.setFrom("nao-responder@dscatalog.com.br");
    helper.addInline("logo", new ClassPathResource("/static/image/logo-ingenico-site.png"));

    emailSender.send(message);

    EmailRegisterRequest dataRegisterMail = new EmailRegisterRequest("dscatalog@gmail.com", email, htmlBody,
        "Confirmação de Cadastro");
    register(dataRegisterMail);
  }

  private void register(EmailRegisterRequest dataRegisterMail) {
    Email email = new Email(dataRegisterMail);
    emailRepository.save(email);
  }

}
