package atm.services;

import atm.domain.BankNote;
import atm.domain.Denomination;

import java.util.Map;

public interface ATMStatusService {
    int bankNotesSum();

    void initializeBankNoteList();

    Map<Denomination, Integer> getAvailableBankNotes();

    void addBankNoteToMap(BankNote bankNote);
}
