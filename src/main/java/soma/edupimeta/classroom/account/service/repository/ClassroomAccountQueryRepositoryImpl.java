package soma.edupimeta.classroom.account.service.repository;

import static soma.edupimeta.account.service.domain.QAccount.account;
import static soma.edupimeta.classroom.account.service.domain.QClassroomAccount.classroomAccount;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import soma.edupimeta.classroom.account.models.ClassroomAccountResponse;
import soma.edupimeta.classroom.account.service.domain.ActionStatus;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccountRole;

@Repository
@RequiredArgsConstructor
public class ClassroomAccountQueryRepositoryImpl implements ClassroomAccountQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long updateActionStatusForClassroom(Long classroomId) {
        return queryFactory
            .update(classroomAccount)
            .set(classroomAccount.actionStatus, ActionStatus.DEFAULT.getValue())
            .where(
                classroomAccount.classroomId.eq(classroomId),
                classroomAccount.actionStatus.ne(ActionStatus.DEFAULT.getValue())
            )
            .execute();

    }

    @Override
    public List<ClassroomAccountResponse> findAllByClassroomId(Long classroomId) {
        return queryFactory
            .select(Projections.constructor(
                ClassroomAccountResponse.class,
                classroomAccount.id,
                account.email,
                account.name,
                classroomAccount.actionStatus,
                classroomAccount.role

            ))
            .from(classroomAccount)
            .leftJoin(account)
            .on(classroomAccount.accountId.eq(account.id))
            .where(
                classroomAccount.classroomId.eq(classroomId),
                classroomAccount.role.eq(ClassroomAccountRole.GUEST)
            )
            .fetch();
    }

}
