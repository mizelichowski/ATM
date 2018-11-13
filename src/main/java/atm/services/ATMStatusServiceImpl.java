package atm.services;

import atm.domain.BankNote;
import atm.repositories.BankNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ATMStatusServiceImpl implements ATMStatusService {

    @Autowired
    private BankNoteRepository bankNoteRepository;

    @Override
    public int bankNotesSum() {
        int bankNoteTotal = 0;
        List<BankNote> allBankNotes = (List<BankNote>) bankNoteRepository.findAll();

        for (BankNote bankNote : allBankNotes) {

            switch (bankNote.getDenomination()) {
                case TWENTY:
                    bankNoteTotal += (bankNote.getAmount() * 20);
                    break;
                case FIFTY:
                    bankNoteTotal += (bankNote.getAmount() * 50);
                    break;
                case HUNDRED:
                    bankNoteTotal += (bankNote.getAmount() * 100);
                    break;
                case TWO_HUNDRED:
                    bankNoteTotal += (bankNote.getAmount() * 200);
                    break;
            }
        }

        return bankNoteTotal;
    }
}
