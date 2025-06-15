package de.hbrs.se2.model.student;

import de.hbrs.se2.model.common.BaseEntity;
import de.hbrs.se2.model.location.Location;
import de.hbrs.se2.model.skill.Skill;
import de.hbrs.se2.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student", schema = "public")
public class Student extends BaseEntity {
    @Builder.Default
    @Column(name = "date_of_birth", nullable = false)
    private Instant date_of_birth = null;

    @Builder.Default
    @Column(name = "first_name")
    private String first_name = "";

    @Builder.Default
    @Column(name = "last_name")
    private String last_name = "";

    @Builder.Default
    @Column(name = "phone", nullable = true)
    private String phone = "";

    @Builder.Default
    @Column(name = "major_study")
    private String major_study = "";

    @Builder.Default
    @Column(name = "degree")
    private String degree = "";

    @Builder.Default
    @Column(name = "description")
    private String description = "";

    @Builder.Default
    @Column(name = "status", nullable = false)
    private String status = "";
    // Wenn Student geloescht wird, wird das entsprechende Location geloescht.


    @Builder.Default
    @OneToOne(orphanRemoval = true, optional = true)
    @JoinColumn(
            name = "location_id",
            referencedColumnName = "id", // den Namen der Spalten in SQL Tabelle
            foreignKey = @ForeignKey(name = "fk_location_id")
    )
    private Location location = null;

    @Builder.Default
    @NotNull
    @OneToOne(orphanRemoval = true, optional = false)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user = null;


    /**
     *
     * @param o
     * @return
     *
     * becaue every single tabel, also among the tabeles, due to having the UUID in the BaseEntity-Class, it is enough to compare the uuid
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!super.equals(o)) return false; wieso solel man das tun, wenn man sowiso auf der base-ebene bereits die id vergibt. somit haben alle Entitäten, von allen tabellen, eine andere uuid

        return getId().equals(((Student) o).getId()); // they must be in the same


    }


}
