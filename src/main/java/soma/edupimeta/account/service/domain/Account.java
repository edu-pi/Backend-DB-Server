package soma.edupimeta.account.service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", updatable = false)
    private Long id;

    @NotNull
    @Column(unique = true, updatable = false)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountRole role;

    @NotNull
    private Boolean isEnabled;

    @NotNull
    private String provider;

    private String phoneNumber;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime pwdUpdatedAt;

    private LocalDateTime lastAccessAt;


    @Builder
    public Account(String email, String password, String name, AccountRole role, String phoneNumber,
        boolean isEnabled, String provider) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.isEnabled = isEnabled;
        this.provider = provider;
        this.createdAt = LocalDateTime.now();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void encodePassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(this.password);
    }

    public void activate() {
        this.isEnabled = true;
    }

}
