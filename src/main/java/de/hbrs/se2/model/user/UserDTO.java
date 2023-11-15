package de.hbrs.se2.model.user;


import java.util.UUID;
public class UserDTO {
    private UUID id;
    private String email;
    private String password;

    public UserDTO(UUID id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static UserDTO toDto(User user){
        UserDTO retUser = new UserDTO(user.getId(), user.getEmail(), user.getPassword());
        return retUser;
    }
}
