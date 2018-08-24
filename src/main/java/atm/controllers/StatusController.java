package atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatusController {

    @GetMapping("/atmstatus")
    public String atmStatus() {
        return "atmstatus";
    }
}
