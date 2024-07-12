package com.pragma.microservicefoodcourt.infrastructure.driving.http.exceptionhandler;

import com.pragma.microservicefoodcourt.domain.exception.DishIsNotFromRestaurantException;
import com.pragma.microservicefoodcourt.domain.exception.EmployeeDoesNotBelongToRestaurantException;
import com.pragma.microservicefoodcourt.domain.exception.UserHasProcessingOrderException;
import com.pragma.microservicefoodcourt.domain.exception.VerificationStatusException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class    OrderControllerAdvisor {
    @ExceptionHandler(DishIsNotFromRestaurantException.class)
    public ResponseEntity<ExceptionResponse> handleDishIsNotFromRestaurantException(DishIsNotFromRestaurantException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.CONFLICT.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UserHasProcessingOrderException.class)
    public ResponseEntity<ExceptionResponse> handleUserHasProcessingOrderException(UserHasProcessingOrderException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(EmployeeDoesNotBelongToRestaurantException.class)
    public ResponseEntity<ExceptionResponse> handleEmployeeDoesNotBelongToRestaurantException(EmployeeDoesNotBelongToRestaurantException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.FORBIDDEN.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(VerificationStatusException.class)
    public ResponseEntity<ExceptionResponse> handleVerificationStatusException(VerificationStatusException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.CONFLICT.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
