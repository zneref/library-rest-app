package zneref.restapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Books")
public class Book {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private int bookId;

    @NotNull
    @Column
    private String title;

    @NotNull
    @Column
    private String author;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column
    private Date publicationDate;

    public Book(@NotNull String title, @NotNull String author, @NotNull Date publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }
}
