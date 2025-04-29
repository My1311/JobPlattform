package de.hbrs.se2.model.user;

import de.hbrs.se2.model.common.BaseEntity;
import de.hbrs.se2.util.Encryption;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "user", schema = "public")
public class User extends BaseEntity {
    @Builder.Default
    @NotNull
    @Column(name = "email", nullable = false)
    private String email = "";

    @NotNull
    @Builder.Default
    @Column(name = "password", nullable = false)
    private String password = "";

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @NotNull
    @Builder.Default
    @Column(name = "role", nullable = false)
    private Set<Role> roles;


    public User() {}

    public User(String email, String password, Set<Role> role) {
        this.email = email;
        this.password = Encryption.sha256(password);
        this.roles = role;
    }

    /**
     *
     * @return the hashed password
     */
    @Override
    public int hashCode() {
       return password.hashCode();
    }
}
