package atm.repositories;

import atm.domain.BankNote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankNoteRepository extends CrudRepository<BankNote, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT amount FROM bank_note WHERE denomination = 'TWENTY'"
    )
    int amountOfBankNotesTwenty();

    @Query(
            nativeQuery = true,
            value = "SELECT amount FROM bank_note WHERE denomination = 'FIFTY'"
    )
    int amountOfBankNotesFifty();

    @Query(
            nativeQuery = true,
            value = "SELECT amount FROM bank_note WHERE denomination = 'HUNDRED'"
    )
    int amountOfBankNotesHundred();

    @Query(
            nativeQuery = true,
            value = "SELECT amount FROM bank_note WHERE denomination = 'TWO_HUNDRED'"
    )
    int amountOfBankNotesTwoHundred();
}
