package com.controller;

import com.model.User;
import com.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @ModelAttribute("User")
    public User getModelAttribute() {
        return new User();
    }
    
    @RequestMapping(value = "")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ModelAndView loginSubmit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user) {
        UserService service = new UserService();
        service.loginUser(user.getLoginName(), user.getPassword());
        request.setAttribute("user", user);
        return new ModelAndView("index");
    }
}
