package com.exampleapi1.Exception;

import com.exampleapi1.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDto> resourceNotFoundException(
            ResourceNotFound r,
            WebRequest req
    ) {
        ErrorDto dto = new ErrorDto();
        dto.setDate(new Date());
        dto.setMessage(r.getMessage());
        dto.setRequest(req.getDescription(true));
        return new ResponseEntity<>(dto,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto>Exception(
           Exception e,
            WebRequest req
    ) {
        ErrorDto dto = new ErrorDto();
        dto.setDate(new Date());
        dto.setMessage(e.getMessage());
        dto.setRequest(req.getDescription(true));
        return new ResponseEntity<>(dto,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
