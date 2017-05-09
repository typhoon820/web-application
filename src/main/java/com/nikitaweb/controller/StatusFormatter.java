package com.nikitaweb.controller;


import com.nikitaweb.model.UserStatusEntity;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;

import javax.jws.soap.SOAPBinding;
import java.text.ParseException;
import java.util.Locale;
@Controller
public class StatusFormatter implements Formatter<UserStatusEntity> {

    @Override
    public UserStatusEntity parse(String s, Locale locale) throws ParseException {
        UserStatusEntity statusEntity = new UserStatusEntity();
        String[] data = s.split("_");
        statusEntity.setId(Integer.parseInt(data[0]));
        statusEntity.setStatus(data[1]);
        return statusEntity;

    }

    @Override
    public String print(UserStatusEntity statusEntity, Locale locale) {
        String res;
        res = String.valueOf(statusEntity.getId()) + "_" + statusEntity.getStatus();
        return res;
    }
}
