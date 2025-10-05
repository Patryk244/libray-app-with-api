package com.library_app_with_api.controller.controllerService;

import org.springframework.http.ResponseEntity;
import java.util.*;

public interface RequiredByController<T> {
    List<?> findAll();
    ResponseEntity<?> create(T objectDto);
}
