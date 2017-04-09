package com.nikitaweb.controller;

import com.nikitaweb.model.UsersEntity;
import com.nikitaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Никита on 05.04.2017.
 */
@Controller
public class IndexController {


    @RequestMapping("/")
    String index(){
        return "index";
    }

}