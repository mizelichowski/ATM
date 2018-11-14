package atm.services;

import atm.domain.BankNote;
import atm.domain.Denomination;
import atm.repositories.BankNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ATMStatusServiceImpl implements ATMStatusService {
    private Map<Denomination, Integer> availableBankNotes = new HashMap<>();

    @Autowired
    private BankNoteRepository bankNoteRepository;

    public Map<Denomination, Integer> getAvailableBankNotes() {
        return availableBankNotes;
    }

    public void setAvailableBankNotes(Map<Denomination, Integer> availableBankNotes) {
        this.availableBankNotes = availableBankNotes;
    }

    @Override
    public int bankNotesSum() {
        initializeBankNoteList();
        int bankNoteTotal = 0;
        List<BankNote> allBankNotes = (List<BankNote>) bankNoteRepository.findAll();

        for (BankNote bankNote : allBankNotes) {

            switch (bankNote.getDenomination()) {
                case TWENTY:
                    addBankNoteToMap(bankNote);
                    bankNoteTotal += (bankNote.getAmount() * 20);
                    break;
                case FIFTY:
                    addBankNoteToMap(bankNote);
                    bankNoteTotal += (bankNote.getAmount() * 50);
                    break;
                case HUNDRED:
                    addBankNoteToMap(bankNote);
                    bankNoteTotal += (bankNote.getAmount() * 100);
                    break;
                case TWO_HUNDRED:
                    addBankNoteToMap(bankNote);
                    bankNoteTotal += (bankNote.getAmount() * 200);
                    break;
            }
        }

        return bankNoteTotal;
    }

    @Override
    public void initializeBankNoteList() {
        availableBankNotes.put(Denomination.TWENTY, 0);
        availableBankNotes.put(Denomination.FIFTY, 0);
        availableBankNotes.put(Denomination.HUNDRED, 0);
        availableBankNotes.put(Denomination.TWO_HUNDRED, 0);
    }

    @Override
    public void addBankNoteToMap(BankNote bankNote) {
        if (bankNote.getAmount() != 0) {
            availableBankNotes.put(bankNote.getDenomination(), bankNote.getAmount());
        }
    }
}
