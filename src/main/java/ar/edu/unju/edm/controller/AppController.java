package ar.edu.unju.edm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.services.AppService;
import ar.edu.unju.edm.util.constants.ViewKeys;
import ar.edu.unju.edm.util.constants.ViewNames;

@Controller
public class AppController {

    @Autowired
    AppService service;

    @GetMapping({"/", "/home"})
    public ModelAndView HomeView() {
        return new ModelAndView(ViewNames.HOME);
    }

    @GetMapping("/explorer")
    public ModelAndView ExplorerView() {
        return new ModelAndView(ViewNames.EXPLORER)
                .addObject(ViewKeys.POINT_LIST, service.getPointList());
    }

    @GetMapping("/error")
    public ModelAndView getError() {
        return new ModelAndView(ViewNames.ERROR);
    }

}