package zneref.restapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "BookCopies")
public class BookCopy {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private int bookCopyId;

    @NotNull
    @Column
    private String status;

    @NotNull
    @Column()
    private int bookId;

    public BookCopy(@NotNull String status, @NotNull int bookId) {
        this.status = status;
        this.bookId = bookId;
    }
}
