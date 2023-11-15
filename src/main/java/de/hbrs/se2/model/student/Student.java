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
    @Column(name = "first_name", nullable = false)
    private String first_name = "";

    @Builder.Default
    @Column(name = "last_name", nullable = false)
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

    public Instant getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Instant date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMajor_study() {
        return major_study;
    }

    public void setMajor_study(String major_study) {
        this.major_study = major_study;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @param o
     * @return
     *
     * becaue every singele tabel, also among the tabeles, due to having the UUID in the BaseEntity-Class, it is enough to compare the uuid
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!super.equals(o)) return false; wieso solel man das tun, wenn man sowiso auf der base-ebene bereits die id vergibt. somit haben alle Entitäten, von allen tabellen, eine andere uuid

        return getId().equals(((Student) o).getId()); // they must be in the same


    }


}
