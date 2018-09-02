package atm.services;

import atm.domain.BankNoteTransfer;

public interface RefillService {
    void refill(BankNoteTransfer refill);
}
