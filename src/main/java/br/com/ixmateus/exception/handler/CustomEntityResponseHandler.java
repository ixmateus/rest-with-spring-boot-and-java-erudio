package br.com.ixmateus.exception.handler;

import br.com.ixmateus.exception.ExcpetionResponse;
import br.com.ixmateus.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExcpetionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExcpetionResponse response = new ExcpetionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

        @ExceptionHandler(ResourceNotFoundException.class)
        public final ResponseEntity<ExcpetionResponse> handleNotFoundtExceptions (Exception ex, WebRequest request){
            ExcpetionResponse response = new ExcpetionResponse(
                    new Date(),
                    ex.getMessage(),
                    request.getDescription(false));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
