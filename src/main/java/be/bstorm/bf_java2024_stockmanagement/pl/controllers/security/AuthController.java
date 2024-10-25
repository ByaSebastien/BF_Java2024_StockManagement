package be.bstorm.bf_java2024_stockmanagement.pl.controllers.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}
