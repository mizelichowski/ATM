package atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ATMHomeController {

    @GetMapping("/home")
    public String index() {
        return "index";
    }
}
