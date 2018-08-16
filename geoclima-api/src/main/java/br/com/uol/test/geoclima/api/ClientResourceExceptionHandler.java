package br.com.uol.test.geoclima.api;

import br.com.uol.test.geoclima.api.util.ApiError;
import br.com.uol.test.geoclima.service.exceptions.GenericErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@ControllerAdvice
public class ClientResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler
    protected ResponseEntity<ApiError> handle(GenericErrorException e) {
        ApiError error = ApiError.builder()
                .code(e.getCode())
                .msg(e.getMsg())
                .build();

        return new ResponseEntity<>(error, resolveAnnotatedResponseStatus(e));
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, Set<String>> errorsMap = fieldErrors.stream().collect(
                Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())
                )
        );
        return new ResponseEntity(errorsMap.isEmpty() ? ex : errorsMap, headers, status);
    }

    private HttpStatus resolveAnnotatedResponseStatus(Exception exception) {
        ResponseStatus annotation = findMergedAnnotation(exception.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }
        return INTERNAL_SERVER_ERROR;
    }
}
