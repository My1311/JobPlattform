package de.hbrs.se2.control.user;

import de.hbrs.se2.model.user.User;
import de.hbrs.se2.model.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public void delete(User user) {
        this.delete(user);
    }
}
