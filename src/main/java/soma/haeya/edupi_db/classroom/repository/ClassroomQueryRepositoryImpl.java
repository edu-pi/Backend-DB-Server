package soma.haeya.edupi_db.classroom.repository;

import static soma.haeya.edupi_db.classroom.domain.QClassroom.classroom;
import static soma.haeya.edupi_db.classroom.domain.QStudent.student;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import soma.haeya.edupi_db.classroom.models.response.ClassroomResponse;

@Repository
@RequiredArgsConstructor
public class ClassroomQueryRepositoryImpl implements ClassroomQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ClassroomResponse> findAllByTeacherIdWithStudentCount(Long teacherId) {
        return queryFactory
            .select(Projections.constructor(
                ClassroomResponse.class,
                classroom.name,
                student.id.count().as("studentCount")  // 학생 수 카운트
            ))
            .from(classroom)
            .leftJoin(student).on(student.classroomId.eq(classroom.id))
            .groupBy(classroom.id)
            .fetch();
    }
}
