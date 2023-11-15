package de.hbrs.se2.model.ratedSkill;


import de.hbrs.se2.model.common.BaseEntity;
import de.hbrs.se2.model.skill.Skill;
import de.hbrs.se2.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
@Table(name = "ratedskill", schema = "public")
public class RatedSkill extends BaseEntity {

    @NotNull
    @Builder.Default
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user = null;

    @NotNull
    @Builder.Default
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "skill_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_skill_id"))
    private Skill skill = null;

    @NotNull
    @Builder.Default
    @Column(name = "value", nullable = false)
    private int value = 0;

}
