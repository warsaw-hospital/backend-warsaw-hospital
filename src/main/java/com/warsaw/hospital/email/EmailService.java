package com.warsaw.hospital.email;

import com.warsaw.hospital.email.entity.EmailEntity;
import com.warsaw.hospital.email.entity.enums.EmailStatusEnum;
import com.warsaw.hospital.emailtemplate.EmailTemplateService;
import com.warsaw.hospital.emailtemplate.entity.EmailTemplateEntity;
import com.warsaw.hospital.exception.ApiException;
import com.warsaw.hospital.user.UserService;
import com.warsaw.hospital.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;

@Transactional
@Service
public class EmailService {
  private final String ERROR_MESSAGE_BASE = "err.email.";

//  @Value("${spring.mail.username}")
//  private String FROM_EMAIL_ADDRESS;

  private final EmailRepository emailRepository;
  private final EmailTemplateService emailTemplateService;
  private final UserService userService;
  private final JavaMailSender mailSender;

  public EmailService(
      EmailRepository emailRepository,
      EmailTemplateService emailTemplateService,
      UserService userService,
      JavaMailSender mailSender) {
    this.emailRepository = emailRepository;
    this.emailTemplateService = emailTemplateService;
    this.userService = userService;
    this.mailSender = mailSender;
  }

  /**
   * This method returns all persisted email entities.
   *
   * @return list of email entities.
   */
  public List<EmailEntity> findAll() {
    return emailRepository.findAll();
  }

  /**
   * This method finds an email by its id. Also, an api exception is thrown, when the email does not
   * exist.
   *
   * @param id email entity id.
   * @return persisted email entity.
   * @throws ApiException when the email does not exist.
   */
  public EmailEntity findById(Long id) throws ApiException {
    return emailRepository
        .findById(id)
        .orElseThrow(
            () -> ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id));
  }

  /**
   * This method persists an email. Also, an api exception is thrown, when the email is already
   * persisted.
   *
   * @param entity email entity.
   * @return persisted email entity.
   * @throws ApiException when the email is already persisted.
   */
  public EmailEntity create(EmailEntity entity) throws ApiException {
    if (emailRepository.existsBySubjectAndDateSent(entity.getSubject(), entity.getDateSent())) {
      throw ApiException.conflict(ERROR_MESSAGE_BASE + "duplicate")
          .addLabel("subject", entity.getSubject())
          .addLabel("dateSent", entity.getDateSent());
    }
    Optional<UserEntity> user = userService.maybeFindByEmail(entity.getUser().getEmail());
    if (user.isPresent()) {
      entity.setUser(user.get());
    } else {
      entity.setUser(null);
    }
    return emailRepository.save(entity);
  }

  /**
   * This method asynchronously sends an email. Also, this method throws an exception, when sending
   * failed.
   *
   * @param emailTemplate email template.
   * @param emailTo receiver email address.
   * @param data map of values, which will be placed into email template.
   * @return a future object of the email.
   * @throws ApiException when email sending failed.
   */
  @Async
  public Future<EmailEntity> send(
      EmailTemplateEntity emailTemplate, String emailTo, Map<String, String> data)
      throws ApiException {
    try {
      MimeMessage message = createMessage(emailTemplate, emailTo, data);
      EmailStatusEnum status = send(message);
      EmailEntity entity = toEntity(message, status, emailTo);
      create(entity);
      return new AsyncResult<>(entity);
    } catch (MessagingException | IOException e) {
      e.printStackTrace();
      throw ApiException.internalError(ERROR_MESSAGE_BASE + "sendingError");
    }
  }

  /**
   * This method creates a mime message from email template, receiver email and template data array.
   * Also, the mime messages support HTML formatting. This method throws exception, when mime
   * message values setting failed.
   *
   * @param emailTemplate email template.
   * @param emailTo receiver email address.
   * @param data map of values, which will be placed into email template.
   * @return mime message ready to be sent.
   * @throws MessagingException when mime message values setting failed.
   */
  private MimeMessage createMessage(
      EmailTemplateEntity emailTemplate, String emailTo, Map<String, String> data)
      throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
//    helper.setFrom(FROM_EMAIL_ADDRESS);
    helper.setTo(emailTo);
    helper.setSubject(emailTemplateService.insertValues(emailTemplate.getSubject(), data));
    helper.setText(emailTemplateService.insertValues(emailTemplate.getBody(), data), true);
    return message;
  }

  /**
   * This method sends mime message using mail sender. It returns FAILED or SUCCEED status.
   *
   * @param message message to send.
   * @return FAILED or SUCCEED.
   */
  private EmailStatusEnum send(MimeMessage message) {
    try {
      mailSender.send(message);
    } catch (MailException e) {
      e.printStackTrace();
      return EmailStatusEnum.FAILED;
    }
    return EmailStatusEnum.SUCCEED;
  }

  /**
   * This method converts a mime message and email status to email entity. Throws exception, when it
   * is not possible to retrieve information from message.
   *
   * @param message email message.
   * @param status email sending status.
   * @return email entity.
   * @throws MessagingException when can not access message content.
   * @throws IOException when can not access message subject or content.
   */
  private EmailEntity toEntity(MimeMessage message, EmailStatusEnum status, String emailTo)
      throws MessagingException, IOException {
    return new EmailEntity()
        .setBody(message.getContent().toString())
        .setSubject(message.getSubject())
        .setDateSent(LocalDateTime.now())
        .setUser(new UserEntity().setEmail(emailTo))
        .setStatus(status);
  }
}
