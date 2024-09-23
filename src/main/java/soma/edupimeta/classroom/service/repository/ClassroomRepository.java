package soma.edupimeta.classroom.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soma.edupimeta.classroom.service.domain.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long>, ClassroomQueryRepository {

}
