package atm.controllers;

import atm.domain.BankNote;
import atm.services.RefillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private RefillService refillService;

    @GetMapping("/home")
    public String index() {
        return "index";
    }

    @PostMapping("/add")
    public String add(Model model, BankNote bankNote) {
        refillService.refillBankNotes(bankNote);
        return "index";
    }
}
