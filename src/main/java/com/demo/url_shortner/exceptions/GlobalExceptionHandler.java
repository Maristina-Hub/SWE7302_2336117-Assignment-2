package com.demo.url_shortner.exceptions;

import com.demo.url_shortner.utils.AppResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException ex, WebRequest request) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value(), request, null), HttpStatus.BAD_REQUEST);
    }

    private AppResponse buildResponse(String message, int responseCode, WebRequest request, List<String> errors) {
        return AppResponse.builder()
                .message(message)
                .build();
    }
}
