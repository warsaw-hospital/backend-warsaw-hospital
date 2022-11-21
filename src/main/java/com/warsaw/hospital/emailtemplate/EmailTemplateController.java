package com.warsaw.hospital.emailtemplate;

import com.warsaw.hospital.emailtemplate.entity.EmailTemplateEntity;
import com.warsaw.hospital.emailtemplate.mappers.EmailTemplateMapper;
import com.warsaw.hospital.emailtemplate.web.request.EmailTemplateCreateRequest;
import com.warsaw.hospital.emailtemplate.web.request.EmailTemplateUpdateRequest;
import com.warsaw.hospital.emailtemplate.web.response.EmailTemplateResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/v1/email-template")
public class EmailTemplateController {
  private final EmailTemplateService emailTemplateService;

  public EmailTemplateController(EmailTemplateService emailTemplateService) {
    this.emailTemplateService = emailTemplateService;
  }

  @Operation(summary = "Find all email templates")
  @GetMapping
  public List<EmailTemplateResponse> findAll() {
    return emailTemplateService.findAll().stream()
        .map(EmailTemplateMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Find an email template")
  @GetMapping("/{id}")
  public EmailTemplateResponse findById(@PathVariable Long id) {
    EmailTemplateEntity entity = emailTemplateService.findById(id);
    return EmailTemplateMapper.toResponse(entity);
  }

  @Operation(summary = "Create an email template")
  @PostMapping
  public EmailTemplateResponse create(
      @Valid @RequestBody EmailTemplateCreateRequest createRequest) {
    EmailTemplateEntity entity = EmailTemplateMapper.toEntity(createRequest);
    return EmailTemplateMapper.toResponse(emailTemplateService.create(entity));
  }

  @Operation(summary = "Update an email template")
  @PutMapping("/{id}")
  public EmailTemplateResponse update(
      @Valid @RequestBody EmailTemplateUpdateRequest updateRequest, @PathVariable Long id) {
    EmailTemplateEntity entity = EmailTemplateMapper.toEntity(updateRequest);
    return EmailTemplateMapper.toResponse(emailTemplateService.update(entity));
  }

  @Operation(summary = "Delete an email template")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    emailTemplateService.delete(id);
  }
}
