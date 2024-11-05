package soma.edupimeta.account.service.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import soma.edupimeta.account.service.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail(String email);

    Optional<Account> findAccountByEmail(String email);

    boolean existsByEmailAndProvider(String email, String provider);

}



