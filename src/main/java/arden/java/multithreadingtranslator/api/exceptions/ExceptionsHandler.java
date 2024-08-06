package arden.java.multithreadingtranslator.api.exceptions;

import arden.java.multithreadingtranslator.api.dto.response.ApiErrorResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(@NotNull MethodArgumentNotValidException e) {
        HttpStatusCode statusCode = e.getStatusCode();
        String description = Arrays.toString(e.getDetailMessageArguments());
        ApiErrorResponse apiErrorResponse =
                buildDefaultErrorResponse(statusCode, description, e);

        return ResponseEntity.status(statusCode).body(apiErrorResponse);
    }

    @ExceptionHandler(TranslatorException.class)
    public ResponseEntity<ApiErrorResponse> handleScrapperException(TranslatorException exception) {
        ApiErrorResponse apiErrorResponse = buildDefaultErrorResponse(exception.getHttpStatusCode(),
                exception.getDescription(), exception
        );

        return ResponseEntity.status(exception.getHttpStatusCode()).body(apiErrorResponse);
    }

    private @NotNull ApiErrorResponse buildDefaultErrorResponse(
            @NotNull HttpStatusCode statusCode,
            String description,
            @NotNull Exception exception
    ) {
        String exceptionName = exception.getClass().getSimpleName();
        String exceptionMessage = exception.getMessage();
        List<String> stacktrace = Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList();

        return new ApiErrorResponse(
                description,
                statusCode.toString(),
                exceptionName,
                exceptionMessage,
                stacktrace
        );
    }
}
