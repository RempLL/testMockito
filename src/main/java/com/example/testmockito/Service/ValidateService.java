package com.example.testmockito.Service;

import com.example.testmockito.Exception.IncorrectNameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidateService {
    public String validateName(String name){
        if(!StringUtils.isAlpha(name)){
            throw new IncorrectNameException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

    public String validateSurname(String surname){
        if(!StringUtils.isAlpha(surname)){
            throw new IncorrectNameException();
        }
        return StringUtils.capitalize(surname.toLowerCase());
    }

}
