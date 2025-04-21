package kk.kertaskerja.program_service.program.web;

import kk.kertaskerja.program_service.common.exception.ApiError;
import kk.kertaskerja.program_service.common.exception.ValidationError;
import kk.kertaskerja.program_service.program.domain.ProgramAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ProgramControllerAdvice {
    @ExceptionHandler(ProgramAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ApiError handleProgramAlreadyExistsException(ProgramAlreadyExistsException ex, ServerHttpRequest request) {
        return new ApiError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage(),
                Instant.now(),
                request.getPath().pathWithinApplication().value()
        );
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ValidationError handleWebExchangeBindException(WebExchangeBindException ex, ServerHttpRequest request) {
        Map<String, List<String>> errors = ex.getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                fieldError -> Optional.ofNullable(fieldError.getDefaultMessage())
                                        .orElse("Format tidak valid"),
                                Collectors.toList()
                        )
                ));
        return new ValidationError(
                HttpStatus.BAD_REQUEST.value(),
                "Form tidak sesuai",
                errors,
                Instant.now(),
                request.getPath().pathWithinApplication().value()
        );
    }
 }
