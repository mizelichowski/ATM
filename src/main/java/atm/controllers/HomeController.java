package atm.controllers;

import atm.services.RefillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private RefillService refillService;

    @GetMapping("/home")
    public String index() {
        return "index";
    }
}
