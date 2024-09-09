package soma.haeya.edupi_db.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soma.haeya.edupi_db.classroom.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Boolean existsByClassroomIdAndUserId(Long classroomId, Long userId);
}
