package com.nikitaweb.controller;


import com.nikitaweb.model.UsersEntity;
import com.nikitaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)

    public String login (Model model){
        return "login";
    }

    /*@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        UsersEntity user = new UsersEntity();
        model.addAttribute("user",user);
        return "registration";
    }*/

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        UsersEntity user = new UsersEntity();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute("user") @Valid UsersEntity user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        UsersEntity userExists = userService.findByLogin(user.getLogin());
        if (userExists != null) {
            bindingResult
                    .rejectValue("login", "error.login",
                            "*There is already a user registered with the login provided");

        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("login", user.getLogin());
            modelAndView.addObject("password", user.getPassword());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }
    /*
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid UsersEntity user, BindingResult bindingResult, Model model){
        UsersEntity userExists = userService.findByLogin(user.getLogin());
        if(userExists != null){
            bindingResult.rejectValue("login",
                    "error.login",
                    "There is already a user registered with that login");
        }
        if(bindingResult.hasErrors()){
            return "registration";
        }
        else{
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been successfully registered");
            model.addAttribute("user", new UsersEntity());
            return "registration";
        }
    }*/
    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersEntity user = userService.findByLogin(auth.getName());
        model.addAttribute("userName", "Welcome "+user.getLogin()+"!!!");
        model.addAttribute("adminMessage", "Content available only for Admins");
        return "admin-home";
    }
}
