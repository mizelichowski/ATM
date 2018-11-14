package atm.controllers;

import atm.domain.BankNoteTransfer;
import atm.services.ATMStatusService;
import atm.services.RefillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ATMRefillController {

    @Autowired
    private RefillService refillService;

    @GetMapping("/refill")
    public String refillForm(Model model) {
        model.addAttribute("refill", new BankNoteTransfer());
        return "refill";
    }

    @PostMapping("/refill")
    public String refillWithBankNotes(Model model, @ModelAttribute BankNoteTransfer refill) {
        List<Integer> addedBankNotes = refillService.displayAddedBanknotes();
        model.addAttribute("addedBankNotes", addedBankNotes);

        refillService.refill(refill);
        return "redirect:/atmstatus";
    }
}
