package be.bstorm.bf_java2024_stockmanagement.pl.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        return "redirect:/login";
    }
}
