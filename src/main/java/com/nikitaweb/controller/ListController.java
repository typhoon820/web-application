package com.nikitaweb.controller;

import com.nikitaweb.model.UsersEntity;
import com.nikitaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ListController {
    @Autowired
    UserService userService;
    /*
    @RequestMapping(value = ("/list"),method = RequestMethod.GET)
    public String listUsers(Model model){
        List<UsersEntity> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "users";
    }*/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getUsers(ModelMap model){
        List<UsersEntity> users = userService.findAllUsers();
       model.put ("users", users);
        return "list";
    }
}
