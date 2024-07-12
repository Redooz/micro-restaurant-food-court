package com.pragma.microservicefoodcourt.domain.api;

import com.pragma.microservicefoodcourt.domain.model.NotificationMethod;

public interface INotifyServicePort {
    void notifyUser(String phone, NotificationMethod method);
}
