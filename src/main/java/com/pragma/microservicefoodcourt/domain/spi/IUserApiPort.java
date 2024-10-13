package com.pragma.microservicefoodcourt.domain.spi;

import com.pragma.microservicefoodcourt.domain.model.User;

public interface IUserApiPort {
    User findUserById(String id);
}
