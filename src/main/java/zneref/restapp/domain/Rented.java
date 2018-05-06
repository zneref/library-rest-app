package zneref.restapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Rented")
public class Rented {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private int rentedId;

    @Temporal(TemporalType.DATE)
    @Column
    private Date rentedFrom;

    @Temporal(TemporalType.DATE)
    @Column
    private Date rentedTo;

    @NotNull
    @Column
    private int bookCopyId;

    @NotNull
    @Column
    private int userId;

    public Rented(Date rentedFrom, Date rentedTo, @NotNull int bookCopyId, @NotNull int userId) {
        this.rentedFrom = rentedFrom;
        this.rentedTo = rentedTo;
        this.bookCopyId = bookCopyId;
        this.userId = userId;
    }
}

