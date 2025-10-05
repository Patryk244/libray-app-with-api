package com.library_app_with_api.repository;

import com.library_app_with_api.domain.Copy;
import com.library_app_with_api.domain.enums.StatusCopy;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(nativeQuery = true)
    List<Copy> findCopyByTitleAndAuthor(@Param("TITLE") String TITLE, @Param("AUTHOR") String AUTHOR);

    @Query(nativeQuery = true)
    Copy findFirstAvailableCopyByTitleAndAuthor(@Param("TITLE") String TITLE, @Param("AUTHOR") String AUTHOR);

    @Modifying
    @Query
    void updateStatusCopyByCopyId(@Param("STATUS") StatusCopy status, @Param("ID") Long id);
}