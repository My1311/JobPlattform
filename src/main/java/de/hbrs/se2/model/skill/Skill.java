package de.hbrs.se2.model.skill;

import de.hbrs.se2.model.common.BaseEntity;
import de.hbrs.se2.model.student.Student;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
@Table(name = "skill", schema = "public")
public class Skill extends BaseEntity {

    @Builder.Default
    @Column(name = "name", nullable = false)
    private String name = "";

}
