package com.library_app_with_api.repository;

import com.library_app_with_api.domain.Loan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findAll();
    @Query(nativeQuery = true)
    boolean readerHasBorrowedBook(@Param("TITLE") String TITLE, @Param("AUTHOR") String AUTHOR,
                                  @Param("READERID") Long READERID);
    @Query(nativeQuery = true)
    List<Loan> titleToReturn(@Param("READERID") Long READERID, @Param("TITLE") String TITLE, @Param("AUTHOR") String AUTHOR);


}