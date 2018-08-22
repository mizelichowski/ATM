package atm.controllers;

import atm.domain.BankNotes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RefillController {

    @GetMapping("/refill")
    public String refillForm() {
        return "refill";
    }

    @PostMapping("/refill")
    public String sendRefill(BankNotes bankNotes, Model model) {
        model.addAttribute(bankNotes);
        return "atmstatus";
    }
}
