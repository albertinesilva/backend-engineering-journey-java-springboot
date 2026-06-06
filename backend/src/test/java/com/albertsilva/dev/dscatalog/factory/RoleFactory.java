package com.albertsilva.dev.dscatalog.factory;

import com.albertsilva.dev.dscatalog.dto.role.response.RoleResponse;

public final class RoleFactory {

  public static final Long OPERATOR_ROLE_ID = 1L;
  public static final Long ADMIN_ROLE_ID = 2L;

  public static RoleResponse createOperatorRoleResponse() {
    return new RoleResponse(OPERATOR_ROLE_ID, "ROLE_OPERATOR");
  }

  public static RoleResponse createAdminRoleResponse() {
    return new RoleResponse(ADMIN_ROLE_ID, "ROLE_ADMIN");
  }
}