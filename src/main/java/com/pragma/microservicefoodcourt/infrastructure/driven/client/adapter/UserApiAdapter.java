package com.pragma.microservicefoodcourt.infrastructure.driven.client.adapter;

import com.pragma.microservicefoodcourt.domain.model.User;
import com.pragma.microservicefoodcourt.domain.spi.IUserApiPort;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.api.IUserFeignClient;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.mapper.IUserDtoMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserApiAdapter implements IUserApiPort {
    private final IUserFeignClient userFeignClient;
    private final IUserDtoMapper userDtoMapper;

    @Override
    public User getUserById(String id) {
        return userDtoMapper.toUser(userFeignClient.findUserByDocumentId(id));
    }
}
