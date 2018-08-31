package atm.controllers;

import atm.domain.BankNote;
import atm.domain.Withdrawal;
import atm.services.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ATMWithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    @GetMapping("/withdraw")
    public String withdraw(Model model) {
        model.addAttribute("withdrawal", new Withdrawal());
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawnBankNotes(Model model, @ModelAttribute Withdrawal withdrawal) {
        model.addAttribute("withdrawal", withdrawal);
        withdrawService.deductBankNotesFromATM(withdrawService.bankNoteSelectionLogic(withdrawal), withdrawal);
        return "withdraw";
    }
}
