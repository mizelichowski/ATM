package atm.services;

import atm.domain.BankNotes;
import atm.repositories.BankNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefillServiceImpl implements RefillService {

    @Autowired
    private BankNoteRepository bankNoteRepository;

    @Override
    public void refillBankNotes(BankNotes bankNotes) {

    }
}
