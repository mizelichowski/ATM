package atm.services;

import atm.domain.BankNote;
import atm.domain.BankNoteTransfer;
import atm.repositories.BankNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RefillServiceImpl implements RefillService {
    private List<Integer> bankNotes = new ArrayList<>();

    @Autowired
    private BankNoteRepository bankNoteRepository;

    @Override
    public void refill(BankNoteTransfer refill) {
        List<BankNote> bankNotes = (List<BankNote>) bankNoteRepository.findAll();
        int refill20PLN = refill.getBankNote20PLNAmt();
        int refill50PLN = refill.getBankNote50PLNAmt();
        int refill100PLN = refill.getBankNote100PLNAmt();
        int refill200PLN = refill.getBankNote200PLNAmt();

        for (BankNote bankNote : bankNotes) {
            addBanknoteToList(bankNote);
            switch (bankNote.getDenomination()) {
                case TWENTY:
                    bankNote.setAmount(bankNote.getAmount() + refill20PLN);
                    bankNoteRepository.save(bankNote);
                    break;
                case FIFTY:
                    bankNote.setAmount(bankNote.getAmount() + refill50PLN);
                    bankNoteRepository.save(bankNote);
                    break;
                case HUNDRED:
                    bankNote.setAmount(bankNote.getAmount() + refill100PLN);
                    bankNoteRepository.save(bankNote);
                    break;
                case TWO_HUNDRED:
                    bankNote.setAmount(bankNote.getAmount() + refill200PLN);
                    bankNoteRepository.save(bankNote);
                    break;
            }
        }
    }

    @Override
    public void addBanknoteToList(BankNote bankNote) {
        this.bankNotes.add(bankNote.getDenomination().getAmount());
    }

    @Override
    public List<Integer> displayAddedBanknotes() {
        return this.bankNotes;
    }
}
