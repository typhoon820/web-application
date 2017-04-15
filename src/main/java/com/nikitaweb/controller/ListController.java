package com.nikitaweb.controller;

import com.nikitaweb.model.SongsEntity;
import com.nikitaweb.service.SongService;
import com.nikitaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.lang.String;

import java.util.List;

@Controller
public class ListController {
    @Autowired
    UserService userService;
    @Autowired
    SongService songService;
    /*
    @RequestMapping(value = ("/list"),method = RequestMethod.GET)
    public String listUsers(Model model){
        List<UsersEntity> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "users";
    }*/
    /*@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getUsers(Model model){
        List<UsersEntity> users;
        users = userService.findAllUsers();
        model.addAttribute ("users", users);
        return "list";
    }*/
    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public String getSongs(Model model){
        List<SongsEntity> songs;
        songs = songService.findAllSongs();
        model.addAttribute("songs",songs);
        return "list";
    }

}
