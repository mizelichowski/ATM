package atm.services;

import atm.domain.BankNote;

import java.util.List;

public interface WithdrawService {
    boolean isSumHigherThanAvailableFunds(int amountToPayOut);
    boolean isSumHigherThan50PLN(int amountToPayOut);
    boolean isPayoutPossible(int amountToPayOut);
    List<BankNote> withdraw(int amountToPayOut);
}
