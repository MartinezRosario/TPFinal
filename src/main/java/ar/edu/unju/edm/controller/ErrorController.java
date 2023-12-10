package ar.edu.unju.edm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
    
    @GetMapping("/error/{code}")
    public ModelAndView getError(@PathVariable("code") String code) {
        return new ModelAndView("error");
    }

}