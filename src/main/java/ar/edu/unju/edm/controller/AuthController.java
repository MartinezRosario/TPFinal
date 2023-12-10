package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.services.LoginService;
import ar.edu.unju.edm.util.CountryList;
import ar.edu.unju.edm.util.constants.ViewKeys;
import ar.edu.unju.edm.util.constants.ViewNames;
import ar.edu.unju.edm.util.types.UserType;
@Controller
public class AuthController {

    @Autowired
    LoginService service;

    @Autowired
    Usuario user;

    @GetMapping("/login")
    public ModelAndView SignIn() {
        ModelAndView view = new ModelAndView(ViewNames.SIGN_IN);
        view.addObject(ViewKeys.USER, user);
        return view;
    }

    @GetMapping("/accounts/new")
    public ModelAndView SignUp() {
        try {
            ModelAndView view = new ModelAndView(ViewNames.SIGN_UP);
            view.addObject(ViewKeys.USER, user);
            view.addObject(ViewKeys.COUNTRY_LIST, CountryList.get());
            return view;
        } catch (Exception exception) {
            return new ModelAndView("error")
                    .addObject("error", exception);
        }
    }

    @PostMapping("/accounts/new")
    public ModelAndView SignUp(@Valid @ModelAttribute(ViewKeys.USER) Usuario user, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return new ModelAndView("redirect:/accounts/new");
            }
            service.addUser(user);
            return new ModelAndView(getRoute(user.getType()));
        } catch (Exception exception) {
            return new ModelAndView(ViewNames.ERROR)
                    .addObject(ViewKeys.ERROR_CODE, exception);
        }
    }

    private String getRoute(String type) {
        return switch (type) {
            case UserType.ADMIN -> "redirect:/administration";
            case UserType.STAFF -> "redirect:/staff";
            case UserType.USER -> "redirect:/explorer";
            default -> "redirect:/";
        };
    }

}