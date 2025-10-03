package com.libray_app_with_api.service;

import com.libray_app_with_api.controller.errorController.exceptions.CopyNotFound;
import com.libray_app_with_api.domain.Copy;
import com.libray_app_with_api.repository.CopyRepository;
import com.libray_app_with_api.service.serviceInterface.DbServicePattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CopyDbService implements DbServicePattern <Copy> {

    private final CopyRepository copyRepository;

    @Override
    public List<Copy> findAll() {
        return copyRepository.findAll();
    }

    @Override
    public Copy save(Copy object) {
        return copyRepository.save(object);
    }

    public long findByTitleAndCount(String title) {
        return copyRepository.findByTitleAndCount(title);
    }

    public Optional<Copy> findCopyById(Long id) {
        return Optional.ofNullable(copyRepository.findById(id)
                .orElseThrow(CopyNotFound::new));
    }


}