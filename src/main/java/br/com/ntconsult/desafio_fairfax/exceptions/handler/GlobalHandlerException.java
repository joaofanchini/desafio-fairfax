package br.com.ntconsult.desafio_fairfax.exceptions.handler;

import br.com.ntconsult.desafio_fairfax.dtos.GlobalHandlerExceptionDto;
import br.com.ntconsult.desafio_fairfax.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalHandlerExceptionDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        GlobalHandlerExceptionDto apiError = new GlobalHandlerExceptionDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
