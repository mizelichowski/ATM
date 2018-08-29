package atm.repositories;

import atm.domain.BankNote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankNoteRepository extends CrudRepository<BankNote, Long> {
}
