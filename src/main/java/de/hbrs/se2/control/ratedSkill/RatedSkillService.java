package de.hbrs.se2.control.ratedSkill;

import de.hbrs.se2.model.ratedSkill.RatedSkill;
import de.hbrs.se2.model.ratedSkill.RatedSkillRepository;
import de.hbrs.se2.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatedSkillService {

    @Autowired
    private RatedSkillRepository ratedSkillRepository;

    public Optional<List<RatedSkill>> findRatedSkillByUser(User user) {
        return this.ratedSkillRepository.findRatedSkillByUserId(user.getId());
    }

    public RatedSkill save(RatedSkill ratedSkill) {
        return this.ratedSkillRepository.save(ratedSkill);
    }

    public void delete(RatedSkill ratedSkill) {
        this.ratedSkillRepository.delete(ratedSkill);
    }
}
