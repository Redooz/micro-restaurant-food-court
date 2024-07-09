package com.pragma.microservicefoodcourt.infrastructure.driving.http.exceptionhandler;

import com.pragma.microservicefoodcourt.domain.exception.PermissionDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class DishControllerAdvisor {
    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<ExceptionResponse> handlePermissionDeniedException(PermissionDeniedException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.FORBIDDEN.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
