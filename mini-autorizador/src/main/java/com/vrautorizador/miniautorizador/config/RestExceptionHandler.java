package com.vrautorizador.miniautorizador.config;

import com.vrautorizador.miniautorizador.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            CartaoInvalidoException.class,
            SenhaInvalidaException.class,
            SaldoInfucienteException.class
    })
    public ResponseEntity<Object> handleArgumentState(final RuntimeException ex) {
        final var apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        log.error(apiError.toString());
        return ResponseEntity.status(apiError.getStatus().value()).body(apiError);
    }


    @ExceptionHandler({
            CartaoInexistenteException.class
    })
    public ResponseEntity<Object> handleArgumentState(final CartaoInexistenteException ex) {
        final var apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        log.error(apiError.toString());
        return ResponseEntity.status(apiError.getStatus().value()).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        final var messages = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        final var apiError = new ApiError(HttpStatus.BAD_REQUEST, messages.toString());
        log.error(apiError.toString());
        return ResponseEntity.status(apiError.getStatus().value()).body(apiError);
    }

}
