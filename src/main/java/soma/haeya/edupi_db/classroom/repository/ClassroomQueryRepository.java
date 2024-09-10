package soma.haeya.edupi_db.classroom.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import soma.haeya.edupi_db.classroom.models.response.ClassroomResponse;

@Repository
public interface ClassroomQueryRepository {

    List<ClassroomResponse> findAllByTeacherIdWithStudentCount(Long teacherId);

}
