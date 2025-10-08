package com.library_app_with_api.repository;

import com.library_app_with_api.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ReaderRepository extends CrudRepository<Reader,Long> {
    List<Reader> findAll();
    Optional<Reader> findById(long id);
}