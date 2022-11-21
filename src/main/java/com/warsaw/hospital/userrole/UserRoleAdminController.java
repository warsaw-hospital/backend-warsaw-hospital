package com.warsaw.hospital.userrole;

import com.warsaw.hospital.userrole.entity.UserRoleEntity;
import com.warsaw.hospital.userrole.mapper.UserRoleMapper;
import com.warsaw.hospital.userrole.web.request.UserRoleCreateRequest;
import com.warsaw.hospital.userrole.web.request.UserRoleUpdateRequest;
import com.warsaw.hospital.userrole.web.response.UserRoleResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/v1/user-role")
public class UserRoleAdminController {
  private final UserRoleService service;

  public UserRoleAdminController(UserRoleService service) {
    this.service = service;
  }

  @Operation(summary = "Get all user roles")
  @GetMapping
  public List<UserRoleEntity> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a user role")
  public UserRoleResponse findById(@PathVariable Long id) {
    UserRoleEntity entity = service.findById(id);
    return UserRoleMapper.toResponse(entity);
  }

  @PostMapping
  @Operation(summary = "Create a user role")
  public UserRoleResponse create(@Valid @RequestBody UserRoleCreateRequest request) {
    UserRoleEntity entity = service.create(UserRoleMapper.toEntity(request));
    return UserRoleMapper.toResponse(entity);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a user role")
  public UserRoleResponse update(
      @PathVariable Long id, @Valid @RequestBody UserRoleUpdateRequest request) {
    UserRoleEntity entity = service.update(UserRoleMapper.toEntity(request.setId(id)));
    return UserRoleMapper.toResponse(entity);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a user role")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
