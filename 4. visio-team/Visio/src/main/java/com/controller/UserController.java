package com.controller;

import com.model.User;
import com.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static String titelNieuw = "Nieuw lid";
    private static String titelWijzig = "Wijzig lid";

    @ModelAttribute("User")
    public User getModelAttribute() {
        return new User();
    }

    @RequestMapping(value = "/userlist")
    public ModelAndView userlijst() throws IOException {
        ModelAndView userListView = new ModelAndView("/user/lijstuser");
        List<User> users = userService.getUsers();
        userListView.addObject("users", users);

        return userListView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView userAddPage() throws IOException {

        return new ModelAndView("user/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView userAdd(@ModelAttribute User user) throws IOException {
        ModelAndView userView = new ModelAndView("/user/lijstuser");
        userService.addUser(user);

        List<User> users = userService.getUsers();
        userView.addObject("users", users);
        String message = "Gebruiker is toegevoegd";
        userView.addObject("message", message);

        return userView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView userDelete(HttpServletRequest request, @PathVariable Integer id) throws IOException {
        ModelAndView userView = new ModelAndView("/user/lijstuser");
        User currentUser = (User) request.getSession().getAttribute("user");

        if (currentUser.getRole() == 0) {
            if (currentUser.equals(userService.getUser(id))) {
                List<User> users = userService.getUsers();
                userView.addObject("users", users);

                String message = "U kunt niet uwzelf verwijderen";
                userView.addObject("message", message);

                return userView;
            }
            userService.deleteUser(id);

            List<User> users = userService.getUsers();
            userView.addObject("users", users);

            String message = "Gebruiker is verwijdered";
            userView.addObject("message", message);

            return userView;
        } else {
            List<User> users = userService.getUsers();
            userView.addObject("users", users);

            String message = "U heeft de rechten niet om deze gebruiker te verwijderen";
            userView.addObject("message", message);

            return userView;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() throws IOException {

        return new ModelAndView("user/login");
        //loginView.addObject("message", "vul gegevens in");
        //TODO: add page /user/login 
        //return loginView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute User user) throws IOException {
        ModelAndView userView;
        User authenticated = userService.loginUser(user.getLoginName(), user.getPassword());
        if (authenticated != null) {
            //TODO: add page /admin
            System.out.println("login is gelukt doorverwijzing naar /admin");
            request.getSession().setAttribute("user", authenticated);
            response.sendRedirect("/visio/");
            userView = new ModelAndView("index");

        } else {
            //hets fout gegaan, geef message mee en lees deze uit in pagina
            System.out.println("login is mislukt, herverwijzing");
            userView = new ModelAndView("user/login");
            userView.addObject("message", "user login is fout gegaan");
        }

        return userView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView userLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("user", null);
        response.sendRedirect("/visio/user/login");
        return null;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable("id") Integer id) throws IOException {

        ModelAndView editUserView = new ModelAndView("/user/edit");
        User user = userService.getUser(id);
        editUserView.addObject("user", user);

        return editUserView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUserView(@ModelAttribute("user") User user) throws IOException {
        ModelAndView userView = new ModelAndView("/user/lijstuser");

        userService.updateUser(user);
        List<User> users = userService.getUsers();
        userView.addObject("users", users);

        String message = "User was succesfully edited.";
        userView.addObject("message", message);

        return userView;
    }

//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public ModelAndView edit(@ModelAttribute("member") Member member) {
//
//        ModelAndView memberlistView = new ModelAndView("/member/lijstlid");
//        coachService.updateMember(member);
//        List<Member> members = coachService.getMembers();
//        memberlistView.addObject("members", members);
//
//        String message = "User was successfully edited.";
//        memberlistView.addObject("message", message);
//        return memberlistView;
//
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public ModelAndView deleteMember(@PathVariable Integer id) {
//        ModelAndView userView = new ModelAndView("/user/lijstuser");
//        userService.deleteUser(id);
//
//        List<User> users = userService.getUsers();
//        userView.addObject("users", users);
//
//        String message = "Member was successfully deleted.";
//        userView.addObject("message", message);
//        return userView;
//    }
//
//    @RequestMapping(value = "/deleteTeam/{id}", method = RequestMethod.GET)
//    public ModelAndView deleteTeam(@PathVariable Integer id) {
//        ModelAndView modelAndView = new ModelAndView("/member/lijstlid");
//        coachService.deleteTeam(id);
//        List<Member> members = coachService.getMembers();
//        modelAndView.addObject("members", members);
//        String message = "Team was successfully deleted.";
//        modelAndView.addObject("message", message);
//        return modelAndView;
//    }
}
