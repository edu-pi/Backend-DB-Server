package soma.edupimeta.classroom.account.service.domain;

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
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@NoArgsConstructor
public class ClassroomAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_account_id", updatable = false)
    private Long id;

    @NotNull
    private Long accountId;

    @NotNull
    private Long classroomId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ClassroomAccountRole role;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public ClassroomAccount(Long accountId, Long classroomId, ClassroomAccountRole role) {
        this.accountId = accountId;
        this.classroomId = classroomId;
        this.role = role;
    }

}
