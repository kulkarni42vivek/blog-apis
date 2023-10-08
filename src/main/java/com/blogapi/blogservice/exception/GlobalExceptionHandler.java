package com.blogapi.blogservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogapi.blogservice.model.ResponseMessage;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            resp.put(fieldName, defaultMessage);
        });
        return new ResponseEntity<>(resp, HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseMessage> handleAccessDenied() {
    	ResponseMessage response = new ResponseMessage();
        response.setErrorMessage("User is not authorized to perform this action");
        response.setErrorCode(400);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handle(Exception ex) {
        ResponseMessage res = new ResponseMessage();
        if (ex instanceof NullPointerException) {
            res.setErrorMessage(ex.getMessage());
            res.setErrorCode(402);
            return ResponseEntity.badRequest().body(res);
        }
        res.setErrorMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(res);


    }
}
