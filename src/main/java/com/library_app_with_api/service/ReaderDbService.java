package com.library_app_with_api.service;

import com.library_app_with_api.controller.errorController.exceptions.ReaderNotFound;
import com.library_app_with_api.domain.Reader;
import com.library_app_with_api.repository.ReaderRepository;
import com.library_app_with_api.service.serviceInterface.DbServicePattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReaderDbService implements DbServicePattern <Reader> {

    private final ReaderRepository readerRepository;

    @Override
    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    @Override
    public Reader save(Reader reader) {
        return readerRepository.save(reader);
    }

    public Optional<Reader> findById(Long id) {
        return Optional.ofNullable(readerRepository.findById(id)
                .orElseThrow(ReaderNotFound::new));
    }
}