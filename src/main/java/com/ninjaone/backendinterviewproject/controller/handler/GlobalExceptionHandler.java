package com.ninjaone.backendinterviewproject.controller.handler;

import com.ninjaone.backendinterviewproject.controller.handler.EndpointErrorMessage;
import com.ninjaone.backendinterviewproject.service.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<EndpointErrorMessage> generateNotFoundException(OrderNotFoundException ex) {
        EndpointErrorMessage message = new EndpointErrorMessage(
                ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<EndpointErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<EndpointErrorMessage> generateNoElementException(NoSuchElementException ex) {
        EndpointErrorMessage message = new EndpointErrorMessage(
                ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<EndpointErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}
