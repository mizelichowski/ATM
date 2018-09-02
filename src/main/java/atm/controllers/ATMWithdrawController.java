package atm.controllers;

import atm.domain.WithdrawalAmount;
import atm.services.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ATMWithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    @GetMapping("/withdraw")
    public String withdraw(Model model, WithdrawalAmount withdrawal) {
        model.addAttribute("withdrawal", withdrawal);
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawnBankNotes(Model model, @ModelAttribute WithdrawalAmount withdrawal) {
        model.addAttribute("withdrawal", withdrawal);
        withdrawService.deductBankNotesFromATM(withdrawService.bankNoteSelectionLogic(withdrawal), withdrawal);
        return "redirect:/atmstatus";
    }
}
