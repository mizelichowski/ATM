package atm.controllers;

import atm.services.ATMStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ATMStatusController {

    @Autowired
    private ATMStatusService atmStatusService;

    @GetMapping("/atmstatus")
    public String atmstatus(Model model) {
        int sumOfMoney = atmStatusService.bankNotesSum();
        model.addAttribute("sumOfMoney", sumOfMoney);
        return "atmstatus";
    }
}
