package com.libray_app_with_api.controller.errorController;

import com.libray_app_with_api.controller.errorController.exceptions.InValidData;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class HttpGlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InValidData.class)
    public ResponseEntity<Object> handleInvalidData(InValidData ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
