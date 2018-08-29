package atm.services;

import atm.repositories.BankNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RefillServiceImpl implements RefillService {

    @Autowired
    private BankNoteRepository bankNoteRepository;

    @Override
    public void refill(Map<String, Integer> refill) {

    }
}
