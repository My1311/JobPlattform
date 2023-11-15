package de.hbrs.se2.model.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@EnableJpaRepositories
public interface SkillRepository extends JpaRepository<Skill, UUID> {

}
