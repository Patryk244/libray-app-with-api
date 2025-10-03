package com.libray_app_with_api.repository;

import com.libray_app_with_api.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {
    List<Title> findAll();
}