package com.library_app_with_api.domain;

import com.library_app_with_api.domain.enums.StatusCopy;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Copy.findByTitleAndCount",
                query = """
                        SELECT COUNT(c.title_id) AS COUNTER FROM copys c INNER JOIN  titles t
                            ON t.title_id = c.title_id WHERE t.title = :TITLE
                       """
        ),
        @NamedNativeQuery(
                name = "Copy.findCopyByTitleAndAuthor",
                query = """
                        SELECT copy_id, status, c.title_id FROM copys c INNER JOIN titles t 
                        ON T.title_id = C.title_id WHERE t.title = :TITLE AND T.author = :AUTHOR""",
                resultClass = Copy.class
        ),
        @NamedNativeQuery(
                name = "Copy.findFirstAvailableCopyByTitleAndAuthor",
                query = """
                        SELECT copy_id, status, c.title_id FROM copys c INNER JOIN titles t ON 
                        t.title_id = c.title_id WHERE t.title = :TITLE AND c.status = 'IN_CIRCULATION'
                        and t.author = :AUTHOR LIMIT 1;
                        """,
                resultClass = Copy.class
        )
})
@NamedQuery(
        name = "Copy.updateStatusCopyByCopyId",
        query = "UPDATE Copy C SET C.status = :STATUS WHERE C.copyId = :ID"
)
@Entity
@Table(name = "copys")
public class Copy {
    @Id
    @GeneratedValue
    private Long copyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "titleId")
    private Title title;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusCopy status;
}
