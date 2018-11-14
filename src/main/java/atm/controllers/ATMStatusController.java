package atm.controllers;

import atm.domain.Denomination;
import atm.services.ATMStatusService;
import atm.services.RefillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ATMStatusController {

    @Autowired
    private ATMStatusService atmStatusService;

    @GetMapping("/atmstatus")
    public String atmstatus(Model model) {
        int sumOfMoney = atmStatusService.bankNotesSum();
        Map<Denomination, Integer> availableBankNotes = new HashMap<>();
        model.addAttribute("availableBankNotes", atmStatusService.getAvailableBankNotes());
        model.addAttribute("sumOfMoney", sumOfMoney);
        return "atmstatus";
    }
}
