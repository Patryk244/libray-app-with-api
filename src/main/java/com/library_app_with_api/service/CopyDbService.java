package com.library_app_with_api.service;

import com.library_app_with_api.controller.errorController.exceptions.CopyNotFound;
import com.library_app_with_api.controller.errorController.exceptions.TitleIsNotAccessToBorrow;
import com.library_app_with_api.domain.Copy;
import com.library_app_with_api.domain.enums.StatusCopy;
import com.library_app_with_api.repository.CopyRepository;
import com.library_app_with_api.service.serviceInterface.DbServicePattern;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CopyDbService implements DbServicePattern<Copy> {

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

    public List<Copy> findCopyByTitleAndAuthor(String title, String author) throws CopyNotFound {
        try {
            List<Copy> copyList = copyRepository.findCopyByTitleAndAuthor(title, author);
            return Objects.requireNonNull(copyList);
        } catch (CopyNotFound copyNotFound) {
            throw new CopyNotFound();
        }
    }

    public Copy findFirstAvailableCopyByTitleAndAuthor(String title, String author) throws TitleIsNotAccessToBorrow {
        try {
            Copy copy = copyRepository.findFirstAvailableCopyByTitleAndAuthor(title, author);
            return Objects.requireNonNull(copy);
        } catch (NullPointerException e) {
            throw new TitleIsNotAccessToBorrow();
        }
    }

    @Transactional
    public void updateStatusCopyByCopyId(StatusCopy status, Long id) {
        copyRepository.updateStatusCopyByCopyId(status, id);
    }

}