package atm.services;

import atm.domain.BankNote;
import atm.domain.Denomination;
import atm.domain.Withdrawal;
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
    public List<BankNote> getBankNoteList() {
        return (List<BankNote>) bankNoteRepository.findAll();
    }

    @Override
    public BankNote getSpecificBankNote(Denomination denomination) {
        BankNote bankNote = bankNoteRepository.findByDenomination(denomination);
        return bankNote;
    }

    @Override
    public int getBankNoteSum() {
        return atmStatusService.bankNotesSum();
    }

    @Override
    public boolean isAvailableFundsExceeded(Withdrawal withdrawal) {
        return withdrawal.getAmount() > getBankNoteSum();
    }

    @Override
    public boolean isPayoutHigherThan50PLN(Withdrawal withdrawal) {
        return withdrawal.getAmount() < 50;
    }

    @Override
    public boolean isPayoutDivisibleBy10(Withdrawal withdrawal) {
        return withdrawal.getAmount() % 10 == 0;
    }

    @Override
    public boolean isPayoutPossible(Withdrawal withdrawal) {
        boolean isPayoutPossible;
        isPayoutPossible = !isAvailableFundsExceeded(withdrawal) && isPayoutHigherThan50PLN(withdrawal)
                && isPayoutDivisibleBy10(withdrawal);
        System.out.println("Payout possible!");

        return isPayoutPossible;
    }

    @Override
    public void bankNoteSelectionLogic(Withdrawal withdrawal) {
        List<BankNote> withdrawnBankNotes = new ArrayList<>();
        int bankNote20PLNAmt = 0;
        int bankNote50PLNAmt = 0;
        int bankNote100PLNAmt = 0;
        int bankNote200PLNAmt = 0;


        while (isPayoutPossible(withdrawal) == true) {
            if (withdrawal.getAmount() % 200 == 0) {
                bankNote200PLNAmt += withdrawal.getAmount() / 200;
            } else if (withdrawal.getAmount() % 200 > 0) {
                bankNote200PLNAmt += withdrawal.getAmount() / 200;
                withdrawal.setAmount(withdrawal.getAmount() - (bankNote200PLNAmt * 200));
            }
        }
    }

}
