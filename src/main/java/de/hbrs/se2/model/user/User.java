package de.hbrs.se2.model.user;

import de.hbrs.se2.model.common.BaseEntity;
import de.hbrs.se2.util.Encryption;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.security.SecureRandom;

@Builder
@ToString
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


    public User() {}

    public User(String email, String password) {
        this.email = email;
        //Password encoding
       // String salt = RandomStringUtils.random(32);
        //this.password = new BCryptPasswordEncoder(16, new SecureRandom()).encode(password + salt);
        this.password = Encryption.sha256(password);
    }

    public boolean checkEmail(String email) {
        return this.email.equals(email);
    }

    /**
     * @param rawPassword
     * @param encodedPassword
     * @return true if the raw password, after encoding, matches the encoded password from storage
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder(16, new SecureRandom()).encode(rawPassword).equals(encodedPassword);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
