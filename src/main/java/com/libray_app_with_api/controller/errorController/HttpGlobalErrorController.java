package com.libray_app_with_api.controller.errorController;

import com.libray_app_with_api.controller.errorController.exceptions.CopyNotFound;
import com.libray_app_with_api.controller.errorController.exceptions.ReaderNotFound;
import com.libray_app_with_api.controller.errorController.exceptions.TitleNotFound;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.*;

@ControllerAdvice
public class HttpGlobalErrorController extends ResponseEntityExceptionHandler {
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<HashMap<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ext) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("message: ", "This title is exist in database");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TitleNotFound.class)
    public ResponseEntity<Object> handleTitleNotFound(TitleNotFound ext) {
        return new ResponseEntity<>("Title Not Found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReaderNotFound.class)
    public ResponseEntity<Object> handleReaderNotFound(ReaderNotFound ext) {
        return new ResponseEntity<>("Reader Not Found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CopyNotFound.class)
    public ResponseEntity<Object> handleCopyNotFound(CopyNotFound ext) {
        return new ResponseEntity<>("Copy Not Found!", HttpStatus.NOT_FOUND);
    }
}