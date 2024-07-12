package com.pragma.microservicefoodcourt.domain.api;

import com.pragma.microservicefoodcourt.domain.model.enums.NotificationMethod;
import com.pragma.microservicefoodcourt.domain.model.enums.VerificationStatus;

public interface IVerificationServicePort {
    VerificationStatus notifyUser(String phone, NotificationMethod method);
}
