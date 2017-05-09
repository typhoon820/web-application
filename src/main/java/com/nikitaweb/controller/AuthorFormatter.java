package com.nikitaweb.controller;

import com.nikitaweb.model.AuthorsEntity;
import com.nikitaweb.model.UserStatusEntity;
import org.springframework.format.Formatter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.util.Locale;

@Controller
public class AuthorFormatter implements Formatter<AuthorsEntity> {
    @Override
    public AuthorsEntity parse(String s, Locale locale) throws ParseException {
        AuthorsEntity statusEntity = new AuthorsEntity();
        String[] data = s.split("_");
        statusEntity.setIdAuthor(Integer.parseInt(data[0]));
        statusEntity.setName(data[1]);
        return statusEntity;
    }

    @Override
    public String print(AuthorsEntity authorsEntity, Locale locale) {
        String res;
        res = String.valueOf(authorsEntity.getIdAuthor()) + "_" + authorsEntity.getName();
        return res;
    }
}
