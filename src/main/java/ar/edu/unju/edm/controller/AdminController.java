package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.edu.unju.edm.services.AdminService;
import ar.edu.unju.edm.util.constants.ViewKeys;
import ar.edu.unju.edm.util.constants.ViewNames;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    AdminService service;

    @GetMapping("/administration")
    public ModelAndView init() {
        return new ModelAndView(ViewNames.ADMIN_PANEL)
                .addObject(ViewKeys.USER_LIST, service.getUserList())
                .addObject(ViewKeys.POINT_LIST, service.getPointList());
    }
}