package de.hbrs.se2.control;

import de.hbrs.se2.control.skill.SkillService;
import de.hbrs.se2.control.user.UserService;
import de.hbrs.se2.model.skill.Skill;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.util.Constant;
import de.hbrs.se2.util.Encryption;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
public class StatupService {

    @Autowired
    private SkillService skillService;
    @Autowired
    private UserService userService;

    @PostConstruct
    private void initSkillsIfNotFound() {
        var skills = this.skillService.findAll();
        if(skills.isEmpty()) {
            insertSkills();
        }
        var users = this.userService.findAllUsers();
        if(users.isEmpty()) {
            insertUser();
        }
    }


    private void insertUser(){
        userService.addUser(User.builder().email("test@test.com")
                .password(Encryption.sha256("test"))
                .roles("Student")
                .build());
    }
    private void insertSkills() {
        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.CPP).build());
        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.CSS).build());
        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.AWS).build());

        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.GCP).build());
        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.GO).build());
        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.AZURE).build());

        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.ANGULAR).build());
        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.SQL).build());
        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.HTML).build());

        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.DOCKER).build());
        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.DEEP_LEARNING).build());
        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.MACHINE_LEARNING).build());

        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.KUBERNETES).build());
        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.PYTHON).build());
        skillService.addSkill(Skill.builder().name(Constant.Value.Skill.JAVA).build());
    }
}
