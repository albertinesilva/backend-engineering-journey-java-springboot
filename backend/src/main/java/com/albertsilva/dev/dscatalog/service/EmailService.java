package com.albertsilva.dev.dscatalog.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

  @Value("${frontend.url}")
  private String frontendUrl;

  @Value("${backend.url}")
  private String backendUrl;

  private final JavaMailSender emailSender;
  private final SpringTemplateEngine templateEngine;
  private final EmailRepository emailRepository;

  /**
   * Construtor para injeção de dependências.
   *
   * @param emailSender     o serviço de envio de e-mails
   * @param templateEngine  o mecanismo de template para geração de conteúdo de
   *                        e-mail
   * @param emailRepository o repositório para persistência de registros de e-mail
   */
  public EmailService(JavaMailSender emailSender, SpringTemplateEngine templateEngine,
      EmailRepository emailRepository) {
    this.emailSender = emailSender;
    this.templateEngine = templateEngine;
    this.emailRepository = emailRepository;
  }

  /**
   * Envia um e-mail de ativação de conta de forma assíncrona.
   *
   * <p>
   * Este método delega o envio do e-mail para uma thread separada,
   * evitando bloquear a requisição principal durante operações de rede
   * com o servidor SMTP.
   * </p>
   *
   * <p>
   * Em caso de falha no envio, o erro é registrado em log e propagado
   * através do {@link CompletableFuture}.
   * </p>
   *
   * @param name            nome do destinatário
   * @param email           endereço de e-mail do destinatário
   * @param activationToken token utilizado para ativação da conta
   * @return um {@link CompletableFuture} representando a execução assíncrona
   *         do envio do e-mail
   */
  @Async
  public CompletableFuture<Void> sendActivationEmailAsync(String name, String email, String activationToken) {

    try {
      sendActivationEmail(name, email, activationToken);

      return CompletableFuture.completedFuture(null);

    } catch (Exception ex) {

      logger.error("Erro ao enviar email de ativação para {}", email, ex);

      return CompletableFuture.failedFuture(ex);
    }
  }

  /**
   * Envia um e-mail para recuperação de senha.
   *
   * <p>
   * O e-mail contém instruções e um token temporário que permitirá
   * ao usuário redefinir sua senha dentro do prazo de validade definido
   * para o token.
   * </p>
   *
   * @param user  usuário que solicitou a recuperação de senha
   * @param token token temporário de recuperação
   */
  public void sendRecoveryEmail(User user, String token) {
  }

  /**
   * Envia um e-mail de ativação de conta.
   *
   * <p>
   * O conteúdo da mensagem é gerado a partir de um template Thymeleaf,
   * contendo informações personalizadas do usuário e um link para
   * confirmação da conta.
   * </p>
   *
   * <p>
   * Após o envio bem-sucedido, um registro do e-mail é persistido
   * para fins de auditoria e rastreabilidade.
   * </p>
   *
   * @param name            nome do destinatário
   * @param email           endereço de e-mail do destinatário
   * @param activationToken token utilizado para ativação da conta
   * @throws MessagingException caso ocorra falha na criação ou envio
   *                            da mensagem de e-mail
   */
  public void sendActivationEmail(String name, String email, String activationToken) throws MessagingException {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

    Context context = new Context();
    context.setVariable("nome", name);
    context.setVariable("titulo", "Bem-vindo ao Dscatalog, " + name + "!");
    context.setVariable("texto",
        "Estamos felizes em tê-lo(a) conosco. Para começar a usar o Dscatalog, confirme seu cadastro clicando no link abaixo.");
    context.setVariable("linkConfirmacao", frontendUrl + "/activate-account?token=" + activationToken);

    String htmlBody = templateEngine.process("activate_user_by_email_template", context);
    helper.setTo(email);
    helper.setText(htmlBody, true);
    helper.setSubject("Confirmação de Cadastro");
    helper.setFrom("nao-responder@dscatalog.com.br");
    helper.addInline("logo", new ClassPathResource("/static/image/logo-ingenico-site.png"));

    emailSender.send(message);
    logger.info("Email de ativação enviado para {}", email);

    EmailRegisterRequest dataRegisterMail = new EmailRegisterRequest("dscatalog@gmail.com", email,
        "Confirmação de Cadastro");
    registerEmailLog(dataRegisterMail);
  }

  /**
   * Persiste informações do e-mail enviado.
   *
   * <p>
   * Este registro pode ser utilizado para auditoria, monitoramento
   * ou rastreamento de mensagens enviadas pelo sistema.
   * </p>
   *
   * @param dataRegisterMail dados do e-mail enviado
   */
  private void registerEmailLog(EmailRegisterRequest dataRegisterMail) {
    Email email = new Email(dataRegisterMail);
    emailRepository.save(email);
  }

}
