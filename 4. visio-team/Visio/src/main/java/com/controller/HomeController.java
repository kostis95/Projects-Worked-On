package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Peter
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
