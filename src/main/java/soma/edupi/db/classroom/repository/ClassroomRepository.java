package soma.edupi.db.classroom.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import soma.edupi.db.classroom.domain.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    Optional<Classroom> findByUserIdAndName(Long userId, String name);
}
