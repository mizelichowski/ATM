package atm.services;

import atm.repositories.BankNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ATMStatusServiceImpl implements ATMStatusService {

    @Autowired
    private BankNoteRepository bankNoteRepository;

    @Override
    public int bankNotesSum() {
        int bankNoteSum;
        int noOf20 = bankNoteRepository.amountOfBankNotesTwenty();
        int noOf50 = bankNoteRepository.amountOfBankNotesFifty();
        int noOf100 = bankNoteRepository.amountOfBankNotesHundred();
        int noOf200 = bankNoteRepository.amountOfBankNotesTwoHundred();

        bankNoteSum = (noOf20 * 20) + (noOf50 * 50) + (noOf100 * 100) + (noOf200 * 200);

        return bankNoteSum;
    }
}
