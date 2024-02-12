package com.musalasoft.eventbooking.exception;

import com.musalasoft.eventbooking.dtos.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomeException {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put("status","1");
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(value
            = NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleNullPointerException(NullPointerException ne){
        ErrorResponse response = new ErrorResponse();
        response.setStatusMessage(ne.getLocalizedMessage());
        response.setStatus("1");
        return response;
    }

    @ExceptionHandler(value = ServletException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleMethodArgumentNotValidException(ServletException ne){
        ErrorResponse response = new ErrorResponse();
        response.setStatusMessage(ne.getLocalizedMessage());
        response.setStatus("1");
        return response;

    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public @ResponseBody ErrorResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ne){
        ErrorResponse response = new ErrorResponse();
        response.setStatusMessage("Content type '' not supported");
        response.setStatus("1");
        return response;
    }

    @ExceptionHandler( UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleHttpMediaTypeNotSupportedException( UnexpectedTypeException ne){
        ErrorResponse response = new ErrorResponse();
        response.setStatusMessage(ne.getMessage());
        response.setStatus("1");
        return response;
    }

    @ExceptionHandler( HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleHttpMediaTypeNotSupportedException( HttpMessageNotReadableException ne){
        ErrorResponse response = new ErrorResponse();
        response.setStatusMessage("Cannot be empty please specify:  CONCERT, CONFERENCE or GAME");
        response.setStatus("1");
        return response;
    }




}
