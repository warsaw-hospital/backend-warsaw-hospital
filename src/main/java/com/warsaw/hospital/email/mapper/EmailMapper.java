package com.warsaw.hospital.email.mapper;


import com.warsaw.hospital.email.entity.EmailEntity;
import com.warsaw.hospital.email.web.response.EmailResponse;

import java.util.List;
import java.util.stream.Collectors;

public class EmailMapper {
  public static EmailResponse toResponse(EmailEntity entity) {
    return new EmailResponse()
        .setStatus(entity.getStatus())
        .setBody(entity.getBody())
        .setSubject(entity.getSubject())
        .setDateSent(entity.getDateSent());
  }

  public static List<EmailResponse> toResponses(List<EmailEntity> entities) {
    return entities.stream().map(EmailMapper::toResponse).collect(Collectors.toList());
  }
}
