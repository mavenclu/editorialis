package cz.cvut.fel.ear.semestralka.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
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
    @JsonIgnore
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;
    @Pattern(regexp = "[a-z ,.'-]+$")
    @NotNull
    @Size(min = 2, max = 20, message = "Name must contain at least 2 letters and at most 20 letters")
    private String firstName;
    @NotNull
    @Pattern(regexp = "[a-z ,.'-]+$")
    @Size(min = 2, max = 20, message = "Last name must contain at least 2 letters and at most 20 letters")
    private String lastName;
    private final String name = firstName + " " + lastName;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role usersRole;

    protected BaseUserEntity() {
        userId = null;
    }



    public String getName() {
        return firstName + " " + lastName;
    }

    public String getUsersRole(){
        return usersRole.name();
    }
    public void setPassword(String password){
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getUsername(){
        return getEmail();
    }

}

