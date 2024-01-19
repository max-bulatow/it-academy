package by.itacademy.web.controller.error;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({MethodValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final String handleException(final MethodValidationException ex) {
        return ex.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .reduce((x, y) -> x + ", " + y)
                .orElse("Validation error");
    }
}
