package com.nikitaweb.Validator;

import com.nikitaweb.model.UsersEntity;
import com.nikitaweb.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass){
        return UsersEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors){
        UsersEntity user = (UsersEntity) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty");
        if (user.getLogin().length()<2 || user.getLogin().length()>32){
            errors.rejectValue("login","Size.userForm.login");
        }
        if (userService.findByLogin(user.getLogin()) != null){
            errors.rejectValue("login", "Duplicate.userForm.login");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","NotEmpty");
        if(user.getPassword().length()<4 ||user.getPassword().length()>32){
            errors.rejectValue("password","Size.userForm.password");
        }
    }
}
