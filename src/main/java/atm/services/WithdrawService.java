package atm.services;

import atm.domain.BankNote;
import atm.domain.BankNoteTransfer;
import atm.domain.Denomination;
import atm.domain.WithdrawalAmount;

public interface WithdrawService {
    BankNote getSpecificBankNote(Denomination denomination);

    int getBankNoteSum();

    boolean isAvailableFundsExceeded(WithdrawalAmount amount);

    boolean isPayoutHigherThan50PLN(WithdrawalAmount amount);

    boolean isPayoutDivisibleBy10(WithdrawalAmount amount);

    boolean isPayoutPossible(WithdrawalAmount amount);

    BankNoteTransfer bankNoteSelectionLogic(WithdrawalAmount amount);

    void deductBankNotesFromATM(BankNoteTransfer withdrawal, WithdrawalAmount amount);
}
