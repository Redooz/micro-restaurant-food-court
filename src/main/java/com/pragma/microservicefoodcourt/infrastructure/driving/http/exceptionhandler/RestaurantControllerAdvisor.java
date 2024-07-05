package com.pragma.microservicefoodcourt.infrastructure.driving.http.exceptionhandler;

import com.pragma.microservicefoodcourt.domain.exception.NotOwnerException;
import com.pragma.microservicefoodcourt.domain.exception.RestaurantOwnerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class RestaurantControllerAdvisor {
    @ExceptionHandler(NotOwnerException.class)
    public ResponseEntity<ExceptionResponse> handleNotOwnerException(NotOwnerException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RestaurantOwnerNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantOwnerNotFoundException(RestaurantOwnerNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(response);
    }
}
