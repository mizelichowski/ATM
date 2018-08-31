package atm.services;

import atm.domain.BankNote;
import atm.domain.Denomination;
import atm.domain.Withdrawal;

import java.util.List;

public interface WithdrawService {
    List<BankNote> getBankNoteList();
    BankNote getSpecificBankNote(Denomination denomination);
    int getBankNoteSum();
    boolean isAvailableFundsExceeded(Withdrawal withdrawal);
    boolean isPayoutHigherThan50PLN(Withdrawal withdrawal);
    boolean isPayoutDivisibleBy10(Withdrawal withdrawal);
    boolean isPayoutPossible(Withdrawal withdrawal);
    void bankNoteSelectionLogic(Withdrawal withdrawal);
}
