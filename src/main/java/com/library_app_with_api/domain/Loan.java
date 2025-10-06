package com.library_app_with_api.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Loan.readerHasBorrowedBook",
                query = """
                            SELECT EXISTS(SELECT 1 from loans l inner join titles t on t.title = :TITLE and
                            l.reader_id = :READERID and t.author = :AUTHOR and l.returned_date is null)
                        """,
                resultClass = Boolean.class
        )
})
@NamedQuery(
        name = "Loan.titleToReturn",
        query = """
        SELECT l FROM Loan l
        JOIN l.copy c
        JOIN c.title t
        WHERE l.reader.id = :READERID
          AND t.title = :TITLE
          AND t.author = :AUTHOR
          AND l.returnedDate IS NULL
        """
)
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "CopyId")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "readerId")
    private Reader reader;

    @Column(name = "borrowedDate", nullable = false)
    private LocalDate borrowedDate;

    @Column(name = "returnedDate")
    private LocalDate returnedDate;

    @Override
    public String toString() {
        return "CopyId: " + copy.getCopyId() + ", readerId: " + reader.getReaderId() + ", borrowedDate: " + borrowedDate
                + ", returnedDate: " + returnedDate;
    }
}

