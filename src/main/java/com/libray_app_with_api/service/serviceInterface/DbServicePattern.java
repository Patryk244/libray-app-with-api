package com.libray_app_with_api.service.serviceInterface;

import java.util.*;

public interface DbServicePattern <T> {
    List<T> findAll();
    T save(T object);
}