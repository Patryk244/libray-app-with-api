package com.libray_app_with_api.service;

import com.libray_app_with_api.domain.Reader;
import com.libray_app_with_api.repository.ReaderRepository;
import com.libray_app_with_api.service.serviceInterface.DbServicePattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}