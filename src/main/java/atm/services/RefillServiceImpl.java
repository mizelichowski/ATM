package atm.services;

import atm.domain.BankNote;
import atm.domain.Refill;
import atm.repositories.BankNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefillServiceImpl implements RefillService {

    @Autowired
    private BankNoteRepository bankNoteRepository;

    @Override
    public void refill(Refill refill) {
        List<BankNote> bankNotes = (List<BankNote>) bankNoteRepository.findAll();
        int refill20PLN = refill.getBankNote20PLNAmt();
        int refill50PLN = refill.getBankNote50PLNAmt();
        int refill100PLN = refill.getBankNote100PLNAmt();
        int refill200PLN = refill.getBankNote200PLNAmt();

        for (BankNote bankNote : bankNotes) {
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
}
