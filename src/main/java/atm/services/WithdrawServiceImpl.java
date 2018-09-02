package atm.services;

import atm.domain.BankNote;
import atm.domain.BankNoteTransfer;
import atm.domain.Denomination;
import atm.domain.WithdrawalAmount;
import atm.repositories.BankNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean isAvailableFundsExceeded(WithdrawalAmount withdrawal) {
        System.out.println("Wyplac: " + withdrawal.getAmount() + " z: " + getBankNoteSum());
        return withdrawal.getAmount() > getBankNoteSum();
    }

    @Override
    public boolean isPayoutHigherThan50PLN(WithdrawalAmount withdrawal) {
        System.out.println("Wyplac minimum 50 z " + getBankNoteSum());
        return withdrawal.getAmount() > 50;
    }

    @Override
    public boolean isPayoutDivisibleBy10(WithdrawalAmount withdrawal) {
        return withdrawal.getAmount() % 10 == 0;
    }

    @Override
    public boolean isPayoutPossible(WithdrawalAmount withdrawal) {
        System.out.println(isPayoutDivisibleBy10(withdrawal));
        System.out.println(!isAvailableFundsExceeded(withdrawal));
        System.out.println(isPayoutHigherThan50PLN(withdrawal));
        return isPayoutDivisibleBy10(withdrawal) && !isAvailableFundsExceeded(withdrawal)
                && isPayoutHigherThan50PLN(withdrawal);
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
            System.out.println("Payout possible!");
            if (afterWithdrawal % 200 == 0) {
                bankNote200PLNAmt = afterWithdrawal / 200;
            } else if (afterWithdrawal % 200 != 0) {
                bankNote200PLNAmt = afterWithdrawal / 200;
                afterWithdrawal -= (bankNote200PLNAmt * 200);
                if (afterWithdrawal % 100 == 0) {
                    bankNote100PLNAmt = afterWithdrawal / 100;
                } else if (afterWithdrawal % 100 != 0) {
                    bankNote100PLNAmt = afterWithdrawal / 100;
                    afterWithdrawal -= (bankNote100PLNAmt * 100);
                    if (afterWithdrawal % 50 == 0) {
                        bankNote50PLNAmt = afterWithdrawal / 50;
                    } else if (afterWithdrawal % 50 != 0) {
                        bankNote50PLNAmt = afterWithdrawal / 50;
                        afterWithdrawal -= (bankNote50PLNAmt * 50);
                        if (afterWithdrawal % 20 == 0) {
                            bankNote20PLNAmt = afterWithdrawal / 20;
                        }
                    }
                }
            }
        } else {
            System.out.println("Something is wrong!!!");
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
        List<BankNote> bankNotes = (List<BankNote>) bankNoteRepository.findAll();

        for (BankNote bankNote : bankNotes) {
            switch (bankNote.getDenomination()) {
                case TWENTY:
                    if (transfer.getBankNote20PLNAmt() != 0) {
                        bankNote.setAmount(bankNote.getAmount() - transfer.getBankNote20PLNAmt());
                        bankNoteRepository.save(bankNote);
                    }
                    break;
                case FIFTY:
                    if (transfer.getBankNote50PLNAmt() != 0) {
                        bankNote.setAmount(bankNote.getAmount() - transfer.getBankNote50PLNAmt());
                        bankNoteRepository.save(bankNote);
                    }
                    break;
                case HUNDRED:
                    if (transfer.getBankNote100PLNAmt() != 0) {
                        bankNote.setAmount(bankNote.getAmount() - transfer.getBankNote100PLNAmt());
                        bankNoteRepository.save(bankNote);
                    }
                    break;
                case TWO_HUNDRED:
                    if (transfer.getBankNote200PLNAmt() != 0) {
                        bankNote.setAmount(bankNote.getAmount() - transfer.getBankNote200PLNAmt());
                        bankNoteRepository.save(bankNote);
                    }
                    break;
            }
        }
    }
}
