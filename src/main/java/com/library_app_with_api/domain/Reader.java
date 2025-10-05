package com.library_app_with_api.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue
    private Long readerId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "creationDate")
    private LocalDate creationDate;

    @Override
    public String toString() {
        return "ReaderId:" + readerId + " firstName: " + firstName + " lastname: " + lastName + " creationDate: " + creationDate;
    }
}