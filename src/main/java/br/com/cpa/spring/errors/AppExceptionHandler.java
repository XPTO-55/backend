package br.com.cpa.spring.errors;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// @RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> responseStatusException(ResponseStatusException ex, WebRequest request) {
        System.out.println(ex.getCause());
        return new ResponseEntity<>(ex, ex.getStatus());
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<?> resourceAlreadyExists(ResourceAlreadyExists ex, WebRequest request) {
        System.out.println(ex.getCause());
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();

        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getConstraintViolations().forEach(cv -> errors.add(cv.getMessage()));

        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException(BadCredentialsException ex,
            WebRequest request) {
        // List<String> errors = new ArrayList<>();

    // ex.getConstraintViolations().forEach(cv -> errors.add(cv.getMessage()));

    // Map<String, List<String>> result = new HashMap<>();
    // result.put("errors", ex);

    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
}

    // @ExceptionHandler(ExpiredJwtException.class)
    // public ResponseEntity<?> expiredJwtException(ExpiredJwtException ex,
    // WebRequest request) {
    // // List<String> errors = new ArrayList<>();

    // // ex.getConstraintViolations().forEach(cv -> errors.add(cv.getMessage()));

// // Map<String, List<String>> result = new HashMap<>();
// // result.put("errors", ex);

// throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token expired");
// }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {
        String errorDescription = e.getLocalizedMessage();
        if (errorDescription == null)
            errorDescription = e.toString();
        return new ResponseEntity<>(errorDescription, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
