package pl.jstk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jstk.constants.ViewNames;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLogin() {
        return ViewNames.LOGIN;
    }
}
