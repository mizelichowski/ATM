package atm.services;

import atm.domain.BankNote;
import atm.repositories.BankNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private ATMStatusService atmStatusService;

    @Autowired
    private BankNoteRepository bankNoteRepository;

    @Override
    public boolean isSumHigherThanAvailableFunds(int amountToPayOut) {
        int availableMoney = atmStatusService.bankNotesSum();
        return amountToPayOut > availableMoney;
    }

    @Override
    public boolean isSumHigherThan50PLN(int amountToPayOut) {
        return amountToPayOut < 50;
    }

    @Override
    public boolean isPayoutPossible(int amountToPayOut) {
        boolean isPayoutPossible;

        if (!isSumHigherThanAvailableFunds(amountToPayOut) && isSumHigherThan50PLN(amountToPayOut)) {
            isPayoutPossible = true;
        } else {
            isPayoutPossible = false;
        }

        return isPayoutPossible;
    }

    @Override
    public List<BankNote> withdraw(int amountToPayOut) {
        List<BankNote> withdrawnBankNotes = new ArrayList<>();
        return withdrawnBankNotes;
    }

}
