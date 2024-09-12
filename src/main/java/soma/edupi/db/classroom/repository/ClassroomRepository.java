package soma.edupi.db.classroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soma.edupi.db.classroom.domain.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long>, ClassroomQueryRepository {

}
