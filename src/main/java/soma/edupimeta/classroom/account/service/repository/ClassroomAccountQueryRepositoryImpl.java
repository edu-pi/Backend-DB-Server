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
    public Long updateActionStatusForClassroom(Long classroomId) {
        return queryFactory
            .update(classroomAccount)
            .set(classroomAccount.actionStatus, ActionStatus.ING.getType())
            .where(
                classroomAccount.classroomId.eq(classroomId),
                classroomAccount.actionStatus.ne(ActionStatus.ING.getType())
            )
            .execute();

    }

}
