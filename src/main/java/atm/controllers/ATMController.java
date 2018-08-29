package atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ATMController {

    @GetMapping("/home")
    public String index() {
        return "index";
    }

    @GetMapping("/atmstatus")
    public String atmstatus() {
        return "atmstatus";
    }

    @GetMapping("/refill")
    public String refillForm() {
        return "refill";
    }
}
