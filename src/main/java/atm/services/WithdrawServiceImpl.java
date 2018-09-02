package atm.services;

import atm.domain.BankNote;
import atm.domain.BankNoteTransfer;
import atm.domain.Denomination;
import atm.domain.WithdrawalAmount;
import atm.repositories.BankNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static atm.domain.Denomination.*;

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
    public boolean isAvailableFundsExceeded(WithdrawalAmount withdrawal) {
        return withdrawal.getAmount() > getBankNoteSum();
    }

    @Override
    public boolean isPayoutHigherThan50PLN(WithdrawalAmount withdrawal) {
        return withdrawal.getAmount() < 50;
    }

    @Override
    public boolean isPayoutDivisibleBy10(WithdrawalAmount withdrawal) {
        return withdrawal.getAmount() % 10 == 0;
    }

    @Override
    public boolean isPayoutPossible(WithdrawalAmount withdrawal) {
        boolean isPayoutPossible;
        isPayoutPossible = !isAvailableFundsExceeded(withdrawal) && isPayoutHigherThan50PLN(withdrawal)
                && isPayoutDivisibleBy10(withdrawal);
        System.out.println("Payout possible!");

        return isPayoutPossible;
    }

    @Override
    public BankNoteTransfer bankNoteSelectionLogic(WithdrawalAmount withdrawal) {
        BankNoteTransfer transfer = new BankNoteTransfer();
        int bankNote20PLNAmt = 0;
        int bankNote50PLNAmt = 0;
        int bankNote100PLNAmt = 0;
        int bankNote200PLNAmt = 0;
        int afterWithdrawal = withdrawal.getAmount();


        if (isPayoutPossible(withdrawal)) {
            if (afterWithdrawal % 200 == 0) {
                bankNote200PLNAmt = afterWithdrawal / 200;
            } else {
                bankNote200PLNAmt = afterWithdrawal / 200;
                afterWithdrawal -= (bankNote200PLNAmt * 200);
                if (afterWithdrawal % 100 == 0) {
                    bankNote100PLNAmt = afterWithdrawal / 100;
                } else {
                    bankNote100PLNAmt = afterWithdrawal / 100;
                    afterWithdrawal -= (bankNote100PLNAmt * 100);
                    if (afterWithdrawal % 50 == 0) {
                        bankNote50PLNAmt = afterWithdrawal / 50;
                    } else {
                        bankNote50PLNAmt = afterWithdrawal / 50;
                        afterWithdrawal -= (bankNote50PLNAmt * 50);
                        if (afterWithdrawal % 20 == 0) {
                            bankNote20PLNAmt = afterWithdrawal / 20;
                        }
                    }
                }
            }
        }

        transfer.setBankNote20PLNAmt(transfer.getBankNote20PLNAmt() + bankNote20PLNAmt);
        System.out.println("Wyplacam: " + bankNote20PLNAmt + " dwudziestek");
        transfer.setBankNote50PLNAmt(transfer.getBankNote50PLNAmt() + bankNote50PLNAmt);
        System.out.println("Wyplacam: " + bankNote50PLNAmt + " piecdziesiatek");
        transfer.setBankNote100PLNAmt(transfer.getBankNote100PLNAmt() + bankNote100PLNAmt);
        System.out.println("Wyplacam: " + bankNote100PLNAmt + " setek");
        transfer.setBankNote200PLNAmt(transfer.getBankNote200PLNAmt() + bankNote200PLNAmt);
        System.out.println("Wyplacam: " + bankNote200PLNAmt + " dwusetek");

        return transfer;
    }

    @Override
    public void deductBankNotesFromATM(BankNoteTransfer transfer, WithdrawalAmount withdrawal) {
        transfer = bankNoteSelectionLogic(withdrawal);

        getSpecificBankNote(TWENTY).setAmount
                (getSpecificBankNote(TWENTY).getAmount() - transfer.getBankNote20PLNAmt());
        bankNoteRepository.save(bankNoteRepository.findByDenomination(TWENTY));

        getSpecificBankNote(Denomination.FIFTY).setAmount
                (getSpecificBankNote(Denomination.FIFTY).getAmount() - transfer.getBankNote50PLNAmt());
        bankNoteRepository.save(bankNoteRepository.findByDenomination(FIFTY));

        getSpecificBankNote(HUNDRED).setAmount
                (getSpecificBankNote(HUNDRED).getAmount() - transfer.getBankNote100PLNAmt());
        bankNoteRepository.save(bankNoteRepository.findByDenomination(HUNDRED));

        getSpecificBankNote(TWO_HUNDRED).setAmount
                (getSpecificBankNote(TWO_HUNDRED).getAmount() - transfer.getBankNote200PLNAmt());
        bankNoteRepository.save(bankNoteRepository.findByDenomination(TWO_HUNDRED));
    }
}
