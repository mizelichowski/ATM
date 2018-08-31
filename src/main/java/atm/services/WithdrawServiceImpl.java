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
    public List<BankNote> bankNoteSelectionLogic(Withdrawal withdrawal) {
        List<BankNote> withdrawnBankNotes = new ArrayList<>();
        int bankNote20PLNAmt = 0;
        int bankNote50PLNAmt = 0;
        int bankNote100PLNAmt = 0;
        int bankNote200PLNAmt = 0;


        while (isPayoutPossible(withdrawal) == true) {
            if (withdrawal.getAmount() % 200 == 0) {
                bankNote200PLNAmt += withdrawal.getAmount() / 200;
            } else {
                bankNote200PLNAmt += withdrawal.getAmount() / 200;
                withdrawal.setAmount(withdrawal.getAmount() - (bankNote200PLNAmt * 200));
                if (withdrawal.getAmount() % 100 == 0) {
                    bankNote100PLNAmt += withdrawal.getAmount() / 100;
                } else {
                    bankNote100PLNAmt += withdrawal.getAmount() / 100;
                    withdrawal.setAmount(withdrawal.getAmount() - (bankNote100PLNAmt * 100));
                    if (withdrawal.getAmount() % 50 == 0) {
                        bankNote50PLNAmt += withdrawal.getAmount() / 50;
                    } else {
                        bankNote50PLNAmt += withdrawal.getAmount() / 50;
                        withdrawal.setAmount(withdrawal.getAmount() - (bankNote50PLNAmt * 50));
                        if (withdrawal.getAmount() % 20 == 0) {
                            bankNote20PLNAmt += withdrawal.getAmount() / 20;
                        } else {
                            bankNote20PLNAmt += withdrawal.getAmount() / 20;
                            withdrawal.setAmount(withdrawal.getAmount() - (bankNote20PLNAmt * 20));
                        }
                    }
                }
            }
        }

        withdrawnBankNotes.add(new BankNote(Denomination.TWENTY, bankNote20PLNAmt));
        withdrawnBankNotes.add(new BankNote(Denomination.FIFTY, bankNote50PLNAmt));
        withdrawnBankNotes.add(new BankNote(Denomination.HUNDRED, bankNote100PLNAmt));
        withdrawnBankNotes.add(new BankNote(Denomination.TWO_HUNDRED, bankNote200PLNAmt));

        return withdrawnBankNotes;
    }

    @Override
    public void deductBankNotesFromATM(List<BankNote> bankNotes) {

        for (BankNote bankNote : bankNotes) {
            switch (bankNote.getDenomination()) {
                case TWENTY:
                    getSpecificBankNote(Denomination.TWENTY).setAmount
                            (getSpecificBankNote(Denomination.TWENTY).getAmount() - bankNote.getAmount());
                case FIFTY:
                    getSpecificBankNote(Denomination.FIFTY).setAmount
                            (getSpecificBankNote(Denomination.FIFTY).getAmount() - bankNote.getAmount());
                case HUNDRED:
                    getSpecificBankNote(Denomination.HUNDRED).setAmount
                            (getSpecificBankNote(Denomination.HUNDRED).getAmount() - bankNote.getAmount());
                case TWO_HUNDRED:
                    getSpecificBankNote(Denomination.TWO_HUNDRED).setAmount
                            (getSpecificBankNote(Denomination.TWO_HUNDRED).getAmount() - bankNote.getAmount());
            }
        }
    }
}
