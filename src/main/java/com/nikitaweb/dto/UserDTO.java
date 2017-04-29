package com.nikitaweb.dto;

import com.sun.org.apache.xpath.internal.operations.String;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String matchPassword;

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getMatchPassword(){
        return matchPassword;
    }

    public void setMatchPassword(String matchPassword){
        this.matchPassword = matchPassword;
    }
}
