package soma.edupi.db.classroom.repository;

import static soma.edupi.db.classroom.domain.QClassroom.classroom;
import static soma.edupi.db.classroomAccount.domain.QClassroomAccount.classroomAccount;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import soma.edupi.db.classroomAccount.domain.ClassroomAccountRole;

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
}
