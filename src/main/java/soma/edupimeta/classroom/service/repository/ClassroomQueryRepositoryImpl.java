package soma.edupimeta.classroom.service.repository;

import static soma.edupimeta.classroom.account.service.domain.QClassroomAccount.classroomAccount;
import static soma.edupimeta.classroom.service.domain.QClassroom.classroom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccountRole;
import soma.edupimeta.classroom.models.MyClassroomResponse;

@Repository
@RequiredArgsConstructor
public class ClassroomQueryRepositoryImpl implements ClassroomQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsHostClassroomByAccountIdAndName(Long accountId, String name) {

        Integer fetchOne = queryFactory
            .selectOne()
            .from(classroomAccount)
            .leftJoin(classroom)
            .on(classroomAccount.accountId.eq(classroom.id))
            .where(
                classroomAccount.accountId.eq(accountId),
                classroomAccount.role.eq(ClassroomAccountRole.HOST),
                classroom.name.eq(name)
            )
            .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public List<MyClassroomResponse> findMyClassrooms(Long accountId) {
        return queryFactory
            .select(Projections.constructor(
                MyClassroomResponse.class,
                classroom.id,
                classroom.name,
                classroomAccount.role,
                classroomAccount.id.count().as("totalPeople")
            ))
            .from(classroom)
            .leftJoin(classroomAccount)
            .on(classroomAccount.classroomId.eq(classroom.id))
            .where(classroomAccount.accountId.eq(accountId))
            .groupBy(classroom.id)
            .fetch();
    }
}
