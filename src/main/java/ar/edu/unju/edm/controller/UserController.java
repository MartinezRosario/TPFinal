package ar.edu.unju.edm.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Comment;
import ar.edu.unju.edm.model.Point;
import ar.edu.unju.edm.model.Review;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.services.UserService;
import ar.edu.unju.edm.util.CountryList;
import ar.edu.unju.edm.util.constants.ViewKeys;
import ar.edu.unju.edm.util.constants.ViewNames;


@Controller
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    Usuario user;
    @Autowired
    Point point;
    @Autowired
    Comment comment;
    @Autowired
    Review review;

    @GetMapping("/accounts/edit!{ID}")
    public ModelAndView editUser(@PathVariable("ID") Integer ID) {
        return new ModelAndView(ViewNames.EDIT_USER)
                .addObject(ViewKeys.USER, service.getUser(ID))
                .addObject(ViewKeys.COUNTRY_LIST, CountryList.get());
    }

    @PostMapping("/accounts/edit")
    public ModelAndView EditUser(@Valid @ModelAttribute(ViewKeys.USER) Usuario user, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return new ModelAndView("redirect:/accounts/new");
            }
            service.editUser(user);
            return new ModelAndView(ViewNames.REDIRECT_TO_ADMIN_PANEL);
        } catch (Exception exception) {
            return new ModelAndView(ViewNames.ERROR)
                    .addObject(ViewKeys.ERROR_CODE, exception);
        }
    }

    @GetMapping("/accounts/delete!{ID}")
    public ModelAndView deleteUser(@PathVariable("ID") Integer ID) {
        service.deleteUser(ID);
        return new ModelAndView(ViewNames.REDIRECT_TO_ADMIN_PANEL);
    }

    @GetMapping("/points/details!{ID}")
    public ModelAndView showPoint(@PathVariable("ID") Integer ID) {
        ModelAndView view = new ModelAndView(ViewNames.VIEW_POINT);
        point = service.getPoint(ID);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails details = (UserDetails) principal;
                user = service.getUser(details.getUsername());
            }
        }
        // View Objects
        view.addObject(ViewKeys.POINT, point);
        view.addObject(ViewKeys.COMMENT_LIST, "");
        // POST Objects
        view.addObject(ViewKeys.COMMENT, comment);
        view.addObject(ViewKeys.REVIEW, review);
        return view;
    }

    @PostMapping("/points/comment")
    public ModelAndView addComment(@ModelAttribute(ViewKeys.COMMENT) Comment comment) {
        try {
            comment.setUser(user);
            comment.setPoint(point);
            service.addComment(comment);
        } catch (Exception exception) {
            return new ModelAndView("error")
                    .addObject(ViewKeys.ERROR_CODE, exception);
        }
        return null;
    }

    @PostMapping("/points/review")
    public ModelAndView addReview(@ModelAttribute(ViewKeys.REVIEW) Review review) {
        review.setUser(user);
        review.setPoint(point);
        service.addReview(review);
        return null;
    }

}