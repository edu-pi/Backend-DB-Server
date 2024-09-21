package soma.edupi.db.account.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import soma.edupi.db.account.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail(String email);

    Optional<Account> findAccountByEmail(String email);
}



