package br.com.uol.test.geoclima.api;

import br.com.uol.test.geoclima.api.util.ApiError;
import br.com.uol.test.geoclima.service.exceptions.GenericErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;
import static org.springframework.http.HttpStatus.*;

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

    private HttpStatus resolveAnnotatedResponseStatus(Exception exception) {
        ResponseStatus annotation = findMergedAnnotation(exception.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }
        return INTERNAL_SERVER_ERROR;
    }
}
