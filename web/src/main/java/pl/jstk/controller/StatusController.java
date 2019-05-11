package pl.jstk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jstk.constants.ViewNames;

@Controller
public class StatusController {

    @GetMapping("/403")
    public String show403() {
        return ViewNames.ACCESSDENIED;
    }
}
