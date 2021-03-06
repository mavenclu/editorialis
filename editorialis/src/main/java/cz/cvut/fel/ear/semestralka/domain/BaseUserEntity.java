package cz.cvut.fel.ear.semestralka.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserEntity {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long userId;

    @Version
    private Long version;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    private String username;
    @JsonIgnore
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;
    @Pattern(regexp = "^[a-zA-Z]{2,}(?: [a-zA-Z]+){0,2}$")
    @NotNull
    @Size(min = 2, max = 20, message = "Name must contain at least 2 letters and at most 20 letters")
    private String firstName;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{2,}(?: [a-zA-Z]+){0,2}$")
    @Size(min = 2, max = 20, message = "Last name must contain at least 2 letters and at most 20 letters")
    private String lastName;
    private final String name = firstName + " " + lastName;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role usersRole;


    protected BaseUserEntity() {
        userId = null;
    }

    public String getUsersRole() {
        return usersRole.name();
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getUsername() {
        return getEmail();
    }


}

