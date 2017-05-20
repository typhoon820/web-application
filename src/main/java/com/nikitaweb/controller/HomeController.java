package com.nikitaweb.controller;

import com.nikitaweb.model.AuthorsEntity;
import com.nikitaweb.model.SongsEntity;
import com.nikitaweb.model.UserStatusEntity;
import com.nikitaweb.model.UsersEntity;
import com.nikitaweb.service.AuthorsService;
import com.nikitaweb.service.SongService;
import com.nikitaweb.service.UserService;
import com.nikitaweb.service.UserStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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
@RequestMapping("/home")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    UserService userService;
    @Autowired
    UserStatusService statusService;
    @Autowired
    SongService songService;
    @Autowired
    AuthorsService authorsService;
    @RequestMapping
    private ModelAndView home(){
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersEntity loggedUser = userService.findByLogin(auth.getName());
        model.addObject("userName" ,"Hello, " + loggedUser.getLogin());
        if (loggedUser.getUserStatus().equals(statusService.findById(1)))
        model.setViewName("admin");
        else model.setViewName("user");
        return model;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersEntity loggedUser = userService.findByLogin(auth.getName());
        model.addObject("userName" ,"Welcome " + loggedUser.getLogin());
        model.addObject("user", new UsersEntity());
        model.addObject("statuses", statusService.findAllStatuses());
        model.setViewName("admin-adduser");
        return model;
    }
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ModelAndView addNewUser(@Valid @ModelAttribute("user") UsersEntity user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        UsersEntity userExists = userService.findByLogin(user.getLogin());

        if (userExists != null) {
            bindingResult
                    .rejectValue("login", "error.login",
                            "*There is already a user registered with the login provided");

        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin-adduser");
        } else {
            logger.info("rabotaet");
            userService.saveUser(user,true);
            logger.info("rabotaet - norm");
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("login", user.getLogin());
            modelAndView.addObject("password", user.getPassword());
            modelAndView.setViewName("admin-adduser");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/addsong", method = RequestMethod.GET)
    public ModelAndView getSongForm(){
        ModelAndView model = new ModelAndView();
        model.addObject("newSong", new SongsEntity());
        model.setViewName("admin-addsong");
        return model;
    }
    @RequestMapping(value = "/addsong", method = RequestMethod.POST)
    public ModelAndView addNewSong(@Valid @ModelAttribute("newSong") SongsEntity song, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        AuthorsEntity authorsExist = authorsService.findByName(song.getAuthor().getName());
        SongsEntity songExist = songService.findByFullSongName
                (song.getSongName(),authorsExist);
        if (songExist != null){
            bindingResult.rejectValue("songName", "error.song", "*This song is already added");
        }
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("admin-addsong");
        }
        else{
            songService.saveSong(song);
            modelAndView.addObject("successMessage", "The song has been added successfully");
            modelAndView.setViewName("admin-addsong");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/favourites", method = RequestMethod.GET)
    public ModelAndView showFavourites(){
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersEntity loggedUser = userService.findByLogin(auth.getName());
        model.addObject("greeting", loggedUser.getLogin()+", there are your favourite ones");
        model.addObject("favSongs", loggedUser.getSongs());
        model.addObject("delSong", new SongsEntity());
        model.setViewName("favourites");
        return model;
    }
    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteFavSong(@ModelAttribute("delSong") SongsEntity delSong, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersEntity loggedUser = userService.findByLogin(auth.getName());
        delSong = songService.findById(delSong.getIdSong());
        if(bindingResult.hasErrors()) model.setViewName("redirect:/home/favourites");
        else{
            userService.deleteSong(loggedUser, delSong);
            model.setViewName("redirect:/home/favourites");
        }
        return model;
    }

}
