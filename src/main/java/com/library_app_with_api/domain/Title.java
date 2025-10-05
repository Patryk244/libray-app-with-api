package com.library_app_with_api.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Year;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "titles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"title", "author"})
        })
public class Title {
    @Id
    @GeneratedValue
    private Long titleId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "dateRelease")
    private Year dateRelease;

    @Override
    public String toString() {
        return "TitleId: " + titleId + " tilte: " + title + " author: " + author + " dateRelease: " + dateRelease;
    }
}