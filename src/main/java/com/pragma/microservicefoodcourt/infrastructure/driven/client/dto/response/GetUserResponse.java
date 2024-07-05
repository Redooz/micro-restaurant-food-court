package com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.response;

import com.pragma.microservicefoodcourt.domain.model.Role;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record GetUserResponse(
        LocalDate birthDate,
        Role role,
        String documentId,
        String email,
        String lastName,
        String name,
        String phone
) {
}
