package soma.edupimeta.classroom.account.service.repository;

import static soma.edupimeta.classroom.account.service.domain.QClassroomAccount.classroomAccount;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import soma.edupimeta.classroom.account.service.domain.ActionStatus;

@Repository
@RequiredArgsConstructor
public class ClassroomAccountQueryRepositoryImpl implements ClassroomAccountQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean updateActionStatusForClassroom(Long classroomId) {
        long updatedCount = queryFactory
            .update(classroomAccount)
            .set(classroomAccount.actionStatus, ActionStatus.ING.getType())
            .where(classroomAccount.id.eq(classroomId))
            .execute();

        return updatedCount > 0;
    }

    @Override
    public Boolean updateActionStatusByClassroomIdAndAccountId(
        Long classroomId,
        Long accountId,
        ActionStatus actionStatus
    ) {
        long updatedCount = queryFactory
            .update(classroomAccount)
            .set(classroomAccount.actionStatus, actionStatus.getType())
            .where(
                classroomAccount.classroomId.eq(classroomId),
                classroomAccount.accountId.eq(accountId)
            )
            .execute();

        return updatedCount > 0;
    }

}
