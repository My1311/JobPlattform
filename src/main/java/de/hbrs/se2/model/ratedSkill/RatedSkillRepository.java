package de.hbrs.se2.model.ratedSkill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RatedSkillRepository extends JpaRepository<RatedSkill, UUID> {

    @Query("SELECT RatedSkill FROM RatedSkill ratedskill where ratedskill.user.id = :userid")
    Optional<List<RatedSkill>> findRatedSkillByUserId(@Param("userid") UUID id);

}
