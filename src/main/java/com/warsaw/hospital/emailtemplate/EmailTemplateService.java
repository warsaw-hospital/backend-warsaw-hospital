package com.warsaw.hospital.emailtemplate;

import com.warsaw.hospital.emailtemplate.entity.EmailTemplateEntity;
import com.warsaw.hospital.emailtemplate.util.StringTemplateManager;
import com.warsaw.hospital.exception.ApiException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class EmailTemplateService {
  private final String ERROR_MESSAGE_BASE = "err.emailtemplate.";
  private final EmailTemplateRepository repository;
  private final StringTemplateManager templateManager;

  public EmailTemplateService(
      EmailTemplateRepository repository, StringTemplateManager templateManager) {
    this.repository = repository;
    this.templateManager = templateManager;
  }

  /**
   * This method returns all email templates.
   *
   * @return email template list with >= size.
   */
  public List<EmailTemplateEntity> findAll() {
    return repository.findAll();
  }

  /**
   * This method returns email template by id. If no such email template is found, then an api
   * exception is thrown.
   *
   * @param id email template id.
   * @return found email template.
   * @throws ApiException when email template with specified id does not exist.
   */
  public EmailTemplateEntity findById(Long id) throws ApiException {
    return repository
        .findById(id)
        .orElseThrow(
            () -> ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id));
  }

  /**
   * This method returns email template by name. If no such email template is found, then an api
   * exception is thrown.
   *
   * @param name email template name.
   * @return found email template.
   * @throws ApiException when email template with specified id does not exist.
   */
  public EmailTemplateEntity findByName(String name) throws ApiException {
    return repository
        .findByName(name)
        .orElseThrow(
            () -> ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("name", name));
  }

  /**
   * This method persists email template. If the email template is found, then an api exception is
   * thrown.
   *
   * @param entity email template entity.
   * @return saved email template (with not null id).
   * @throws ApiException when a duplicate is found.
   */
  public EmailTemplateEntity create(EmailTemplateEntity entity) throws ApiException {
    if (repository.existsByName(entity.getName())) {
      throw ApiException.conflict(ERROR_MESSAGE_BASE + "duplicate")
          .addLabel("name", entity.getName());
    }
    return repository.save(entity);
  }

  /**
   * This method updates a saved email template. If no such email template is found, then an api
   * exception is thrown.
   *
   * @param entity email template entity.
   * @return updated email template.
   * @throws ApiException when email template with specified id does not exist.
   */
  public EmailTemplateEntity update(EmailTemplateEntity entity) throws ApiException {
    if (!repository.existsById(entity.getId())) {
      throw ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", entity.getId());
    }
    return repository.save(entity);
  }

  /**
   * This method deletes email template by its id. If no such email template is found, then an api
   * exception is thrown.
   *
   * @param id email template id.
   * @throws ApiException when email template with specified id does not exist.
   */
  public void delete(Long id) throws ApiException {
    if (!repository.existsById(id)) {
      throw ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id);
    }
    repository.deleteById(id);
  }

  /**
   * This method inserts values into email template body by using a data map.
   *
   * @param text email template body
   * @param data values to change (between ${{ and }}$ )
   * @return string with inserted values.
   */
  public String insertValues(String text, Map<String, String> data) {
    templateManager.setText(text).setData(data);
    return templateManager.process();
  }
}
