package atm.repositories;

import atm.domain.BankNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankNoteRepository extends JpaRepository<BankNote, Long> {
}
