package com.pragma.microservicefoodcourt.domain.spi;

import com.pragma.microservicefoodcourt.domain.model.Traceability;

public interface ITraceabilityApiPort {
    Traceability findTraceabilityByOrderId(Long orderId);
    void saveTraceability(Traceability traceability);
    void updateTraceability(Long orderId, Traceability traceability);
}
