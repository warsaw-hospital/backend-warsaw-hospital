package com.warsaw.hospital.emailtemplate.mappers;

import com.warsaw.hospital.emailtemplate.entity.EmailTemplateEntity;
import com.warsaw.hospital.emailtemplate.web.request.EmailTemplateCreateRequest;
import com.warsaw.hospital.emailtemplate.web.request.EmailTemplateUpdateRequest;
import com.warsaw.hospital.emailtemplate.web.response.EmailTemplateResponse;

import javax.validation.Valid;

public class EmailTemplateMapper {
  public static EmailTemplateResponse toResponse(EmailTemplateEntity entity) {
    return new EmailTemplateResponse()
        .setId(entity.getId())
        .setName(entity.getName())
        .setSubject(entity.getSubject())
        .setBody(entity.getBody());
  }

  public static EmailTemplateEntity toEntity(@Valid EmailTemplateCreateRequest request) {
    return new EmailTemplateEntity()
        .setName(request.getName())
        .setSubject(request.getSubject())
        .setBody(request.getBody());
  }

  public static EmailTemplateEntity toEntity(@Valid EmailTemplateUpdateRequest request) {
    return new EmailTemplateEntity()
        .setId(request.getId())
        .setName(request.getName())
        .setSubject(request.getSubject())
        .setBody(request.getBody());
  }
}
