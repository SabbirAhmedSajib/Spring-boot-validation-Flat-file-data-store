package com.learning.season.exception;

import com.learning.season.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseError handleCustomException(ConstraintViolationException ex) {
        ResponseError responseError = new ResponseError();
        List<String> errorMessages = new ArrayList();
        for (ConstraintViolation constraintViolation : ex.getConstraintViolations()) {
            errorMessages.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        }
        responseError.setErrorMessage(errorMessages);
        responseError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return responseError;

    }
}