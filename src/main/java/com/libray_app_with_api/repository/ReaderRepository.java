package com.libray_app_with_api.repository;

import com.libray_app_with_api.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends CrudRepository<Reader,Long> {
    List<Reader> findAll();
    Optional<Reader> findById(long id);
}