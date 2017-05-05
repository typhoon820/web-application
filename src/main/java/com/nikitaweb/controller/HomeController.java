package com.nikitaweb.controller;

import com.nikitaweb.model.UserStatusEntity;
import com.nikitaweb.model.UsersEntity;
import com.nikitaweb.service.UserService;
import com.nikitaweb.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    UserStatusService statusService;

   @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView homeAdmin() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersEntity loggedUser = userService.findByLogin(auth.getName());
        model.addObject("userName" ,"Welcome " + loggedUser.getLogin());
        model.addObject("user", new UsersEntity());
        model.setViewName("home-admin");
        return model;
    }
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ModelAndView addNewUser(@Valid @ModelAttribute("user") UsersEntity user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        UsersEntity userExists = userService.findByLogin(user.getLogin());
        if (userExists != null) {
            bindingResult
                    .rejectValue("login", "error.login",
                            "*There is already a user registered with the login provided");

        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("home-admin");
        } else {
            userService.saveUser(user,true);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("login", user.getLogin());
            modelAndView.addObject("password", user.getPassword());
            modelAndView.setViewName("home-admin");

        }
        return modelAndView;
    }

}
