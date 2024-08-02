package soma.haeya.edupi_db.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;
import soma.haeya.edupi_db.member.dto.response.LoginResponse;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long id;

    @NotNull
    @Column(unique = true, updatable = false)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String role;

    @NotNull
    private Boolean is_enabled;

    private String phoneNumber;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime pwdUpdatedAt;

    private LocalDateTime lastAccessAt;

    @Builder
    public Member(String email, String password, String name, String role, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.is_enabled = true;
    }

    public LoginResponse of() {
        return new LoginResponse(email, name, role);
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

}
