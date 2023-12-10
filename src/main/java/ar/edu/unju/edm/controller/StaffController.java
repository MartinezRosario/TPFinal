package ar.edu.unju.edm.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Point;
import ar.edu.unju.edm.services.StaffService;
import ar.edu.unju.edm.util.constants.ViewKeys;
import ar.edu.unju.edm.util.constants.ViewNames;


@Controller
public class StaffController {

    @Autowired
    StaffService service;

    @Autowired
    Point point;

    @GetMapping("/staff")
    public ModelAndView init() {
        try {
            return new ModelAndView(ViewNames.STAFF_PANEL)
                    .addObject(ViewKeys.POINT_LIST, service.getPointList());
        } catch (Exception exception) {
            return new ModelAndView(ViewNames.ERROR)
                    .addObject(ViewKeys.ERROR_CODE, exception);
        }
    }

    @GetMapping("/points/new")
    public ModelAndView addPoint() {
        try {
            return new ModelAndView(ViewNames.ADD_POINT)
                    .addObject(ViewKeys.POINT, point);
        } catch (Exception exception) {
            return new ModelAndView(ViewNames.ERROR)
                    .addObject(ViewKeys.ERROR_CODE, exception);
        }
    }
   
    @PostMapping(value="/points/new", consumes="multipart/form-data")
    public ModelAndView setPoint(@ModelAttribute(ViewKeys.POINT) Point point, @RequestParam("file") MultipartFile[] foto) {
        ModelAndView modelAndView= new ModelAndView("redirect:/explorer");
        try{
            byte[] contenido=foto[0].getBytes();
            String base64= Base64.getEncoder().encodeToString(contenido);
            point.setPhoto(base64);
        service.addPoint(point);
        }catch(Exception e){
            modelAndView.addObject("Error", e.getMessage());
        }
        return modelAndView;
    }
    @GetMapping("/points/edit!{ID}")
    public ModelAndView EditPoint(@PathVariable("ID") Integer ID) {
        return new ModelAndView(ViewNames.EDIT_POINT)
                .addObject(ViewKeys.POINT, service.getPoint(ID));
    }

    @PostMapping("/points/edit")
    public ModelAndView EditPoint(@ModelAttribute(ViewKeys.POINT) Point point) {
        service.editPoint(point);
        return new ModelAndView(ViewNames.REDIRECT_TO_EXPLORER);
    }

    @GetMapping("/points/delete!{ID}")
    public ModelAndView DeletePoint(@PathVariable("ID") Integer ID) {
        service.deletePoint(ID);
        return new ModelAndView(ViewNames.REDIRECT_TO_STAFF_PANEL);
    }

}