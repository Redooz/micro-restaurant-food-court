package com.pragma.microservicefoodcourt.domain.api;

import com.pragma.microservicefoodcourt.domain.model.NotificationMethod;

public interface IVerificationServicePort {
    String notifyUser(String phone, NotificationMethod method);
}
