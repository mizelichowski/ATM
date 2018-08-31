package atm.repositories;

import atm.domain.BankNote;
import atm.domain.Denomination;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankNoteRepository extends CrudRepository<BankNote, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM atm.bank_note WHERE denomination = :denomination"
    )
    BankNote findByDenomination(@Param("denomination") Denomination denomination);
}
