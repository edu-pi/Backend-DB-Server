package soma.edupimeta.classroom.service.repository;

import static soma.edupimeta.classroom.service.domain.QClassroom.classroom;
import static soma.edupimeta.classroomAccount.service.domain.QClassroomAccount.classroomAccount;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import soma.edupimeta.classroom.models.ClassroomResponse;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccountRole;

@Repository
@RequiredArgsConstructor
public class ClassroomQueryRepositoryImpl implements ClassroomQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean existsHostClassroomByAccountIdAndName(Long accountId, String name) {

        Integer fetchOne = queryFactory
            .selectOne()
            .from(classroomAccount)
            .leftJoin(classroom)
            .on(classroomAccount.accountId.eq(classroom.id))
            .where(
                classroomAccount.accountId.eq(accountId),
                classroomAccount.role.eq(ClassroomAccountRole.ROLE_HOST),
                classroom.name.eq(name)
            )
            .fetchFirst();

        return fetchOne != null;
    }

    public List<ClassroomResponse> findAllByAccountIdWithGuestCount(Long accountId) {
        return queryFactory
            .select(Projections.constructor(
                ClassroomResponse.class,
                classroom.id,
                classroom.name,
                classroomAccount.id.count().as("guestCount")  // 학생 수 카운트
            ))
            .from(classroom)
            .leftJoin(classroomAccount).on(classroomAccount.classroomId.eq(classroom.id))
            .groupBy(classroom.id)
            .fetch();
    }
}
