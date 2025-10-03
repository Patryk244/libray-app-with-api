package com.libray_app_with_api.repository;

import com.libray_app_with_api.domain.Copy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CopyRepository extends CrudRepository<Copy, Long> {
    List<Copy> findAll();
    Optional<Copy> findById(Long id);
    @Query
    long findByTitleAndCount(@Param("TITLE") String TITLE);
}