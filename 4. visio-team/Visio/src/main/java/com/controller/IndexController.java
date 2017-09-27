package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping(value = "/menu")
    public ModelAndView menu() {
        return new ModelAndView("menu");
    }

    @RequestMapping(value = "/setup")
    public ModelAndView setup() {
        return new ModelAndView("setup");
    }
}
