package atm.services;

import atm.domain.BankNote;
import atm.domain.BankNoteTransfer;

import java.util.List;

public interface RefillService {
    void refill(BankNoteTransfer refill);
    void addBanknoteToList(BankNote bankNote);
    List<Integer> displayAddedBanknotes();
}
