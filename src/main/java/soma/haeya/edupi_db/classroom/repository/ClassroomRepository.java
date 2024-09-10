package soma.haeya.edupi_db.classroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soma.haeya.edupi_db.classroom.domain.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long>, ClassroomQueryRepository {

    Boolean existsByTeacherIdAndName(Long userId, String name);
}
