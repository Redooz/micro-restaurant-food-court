package com.pragma.microservicefoodcourt.infrastructure.driven.client.adapter;

import com.pragma.microservicefoodcourt.domain.model.Traceability;
import com.pragma.microservicefoodcourt.domain.spi.ITraceabilityApiPort;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.api.ITraceabilityFeignClient;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request.CreateTraceabilityRequest;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request.UpdateTraceabilityRequest;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.response.GetTraceabilityResponse;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.mapper.ITraceabilityDtoMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceabilityApiAdapter implements ITraceabilityApiPort {
    private final ITraceabilityFeignClient traceabilityFeignClient;
    private final ITraceabilityDtoMapper traceabilityDtoMapper;

    @Override
    public Traceability findTraceabilityByOrderId(Long orderId) {
        GetTraceabilityResponse traceabilityResponse = traceabilityFeignClient.getTraceabilityByOrderId(orderId);
        return traceabilityDtoMapper.toModelFromResponse(traceabilityResponse);
    }

    @Override
    public void saveTraceability(Traceability traceability) {
        CreateTraceabilityRequest createTraceabilityRequest = traceabilityDtoMapper.toCreateFromModel(traceability);

        traceabilityFeignClient.createTraceability(createTraceabilityRequest);
    }

    @Override
    public void updateTraceability(Long orderId, Traceability traceability) {
        UpdateTraceabilityRequest updateTraceabilityRequest = traceabilityDtoMapper.toUpdateFromModel(traceability);

        traceabilityFeignClient.updateTraceability(orderId, updateTraceabilityRequest);
    }
}
