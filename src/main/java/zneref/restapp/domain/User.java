package zneref.restapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

@NamedQuery(
        name = "User.findByNameOrLastName",
        query = "FROM Users WHERE name LIKE :ARG OR last_name LIKE :ARG"
)

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private int userId;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String lastName;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column
    private Date created;

    public User(@NotNull String name, @NotNull String lastName, @NotNull Date created) {
        this.name = name;
        this.lastName = lastName;
        this.created = created;
    }
}
