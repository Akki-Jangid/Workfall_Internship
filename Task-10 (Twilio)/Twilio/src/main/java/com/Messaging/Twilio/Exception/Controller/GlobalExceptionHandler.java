package com.Messaging.Twilio.Exception.Controller;

import com.Messaging.Twilio.Controller.ApiResponse;
import com.Messaging.Twilio.Exception.TwilioException.TwilioCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TwilioCustomException.class)
    public ResponseEntity<ApiResponse> handleTwilioExceptions(TwilioCustomException ex){
        ApiResponse response = new ApiResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex){
        ApiResponse response = new ApiResponse(false, ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
