package de.hbrs.se2.control.skill;

import de.hbrs.se2.model.skill.Skill;
import de.hbrs.se2.model.skill.SkillRepository;
import de.hbrs.se2.model.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public @Nullable Skill addSkill(Skill skill) {
        return this.skillRepository.save(skill);
    }

    public List<Skill> findAll() {
        return this.skillRepository.findAll();
    }

    public void delete(Skill skill) {
        this.skillRepository.delete(skill);
    }

//    public Skill findSkillByStudent(Student student) {
//        return this.skillRepository.findSkillByStudentId(student.getId());
//    }

}
