package com.pragma.microservicefoodcourt.infrastructure.driven.client.mapper;

import com.pragma.microservicefoodcourt.domain.model.User;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.response.GetUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserDtoMapper {
    @Mapping(target = "password", ignore = true)
    User toUser(GetUserResponse getUserResponse);
}
