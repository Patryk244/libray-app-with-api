package com.libray_app_with_api.domain;

import com.libray_app_with_api.domain.enums.StatusCopy;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(
        name = "Copy.findByTitleAndCount",
        query = "select COUNT(c.title_id) as copy_of_books FROM copys c INNER JOIN\n" +
                "    titles t on t.title_id = c.title_id\n" +
                "where t.title = :TITLE"
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
