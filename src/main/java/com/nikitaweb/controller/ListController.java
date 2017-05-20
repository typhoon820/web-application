package com.nikitaweb.controller;

import com.nikitaweb.model.AuthorsEntity;
import com.nikitaweb.model.SongsEntity;
import com.nikitaweb.model.UsersEntity;
import com.nikitaweb.service.AuthorsService;
import com.nikitaweb.service.SongService;
import com.nikitaweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.lang.String;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ListController {
    private static final Logger logger = LoggerFactory.getLogger(ListController.class);
    @Autowired
    UserService userService;
    @Autowired
    SongService songService;
    @Autowired
    AuthorsService authorsService;
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
    @RequestMapping(value = "/songs", method = RequestMethod.GET)//TODO: КАК то замутить добавление в скачанные песни
    public String getSongs(Model model){
        List<SongsEntity> songs;
        songs = songService.findAllSongs();
        model.addAttribute("songs",songs);
        model.addAttribute("song", new SongsEntity());
        return "list";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveSong(@ModelAttribute("song") SongsEntity song, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();
        SongsEntity newSong = songService.findById(song.getIdSong());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersEntity loggedUser = userService.findByLogin(auth.getName());

        if(loggedUser.getSongs().contains(newSong)) model.setViewName("redirect:/songs");
        else {
            if (bindingResult.hasErrors()) model.setViewName("redirect:/songs");
            else {
                logger.info("==_+_+_+_+_+__+_+++==" + song.getIdSong());
                userService.addSong(loggedUser, newSong);
                model.setViewName("redirect:/songs");
            }
        }
        return model;
    }
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(){
        ModelAndView model = new ModelAndView();
        String srch="";
        model.addObject("searchStr", srch);
        model.setViewName("search-results");
        return model;
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchResult(@ModelAttribute("searchStr") String searchStr, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();
        List<SongsEntity> result = songService.findByName(searchStr);
        AuthorsEntity author = authorsService.findByName(searchStr);
        result.addAll(songService.findByAuthor(author));
        if (bindingResult.hasErrors()) model.setViewName("redirect:/songs");
        else{
            if(!result.isEmpty()) model.addObject("result", result);
            else model.addObject("failMessage", "Unfortunately, no matches were found.");
            model.setViewName("search-results");
        }
        return model;
    }

}
