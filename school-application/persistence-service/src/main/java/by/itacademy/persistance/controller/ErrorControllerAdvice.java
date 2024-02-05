package by.itacademy.persistance.controller;

import by.itacademy.persistance.controller.dto.ErrorDto;
import by.itacademy.persistance.persistence.exception.ValueNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleGlobalException(final Exception ex) {
        final var uuid = UUID.randomUUID();

        log.error("[%s] Global error".formatted(uuid), ex);

        return new ErrorDto(uuid, "Something went wrong, please try again");
    }

    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationException(final Exception ex) {
        final var uuid = UUID.randomUUID();

        log.error("[%s] Global error".formatted(uuid), ex);

        return new ErrorDto(uuid, "Something went wrong, please try again");
    }

    @ExceptionHandler({ValueNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleValidationException(final ValueNotFoundException ex) {
        final var uuid = UUID.randomUUID();

        log.warn("[{}] " + ex.getMessage(), uuid);

        return new ErrorDto(uuid, ex.getMessage());
    }
}
