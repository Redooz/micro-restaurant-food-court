package com.pragma.microservicefoodcourt.infrastructure.driven.client.mapper;

import com.pragma.microservicefoodcourt.domain.model.Traceability;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request.CreateTraceabilityRequest;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request.UpdateTraceabilityRequest;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.response.GetTraceabilityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITraceabilityDtoMapper {
    CreateTraceabilityRequest toCreateFromModel(Traceability traceability);

    UpdateTraceabilityRequest toUpdateFromModel(Traceability traceability);

    Traceability toModelFromResponse(GetTraceabilityResponse getTraceabilityResponse);

    List<Traceability> toModelListFromResponseList(List<GetTraceabilityResponse> getTraceabilityResponses);
}
