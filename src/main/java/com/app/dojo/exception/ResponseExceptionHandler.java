package com.app.dojo.exception;

import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequest.class)
    public final ResponseEntity<ExceptionResponse> handlerBadRequestException(BadRequest badRequest, WebRequest webRequest){
        ExceptionResponse exceptionResponse= new ExceptionResponse(LocalDateTime.now(), badRequest.getMessage(),webRequest.getDescription(false));
        return  new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerNotFoundException(NotFoundException notFoundException, WebRequest request){
        ExceptionResponse  exceptionResponse= new ExceptionResponse(LocalDateTime.now(), notFoundException.getMessage(), request.getDescription(false));
        return  new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
