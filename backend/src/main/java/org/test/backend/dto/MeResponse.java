package org.test.backend.dto;


import org.test.backend.enums.Role;

import java.util.UUID;

public record  MeResponse(UUID id,String email, Role role) {
}
