package com.mohamed.movieticketsystem.entities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation  {

final private String strRegEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
        "(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
Pattern pattern = Pattern.compile(strRegEx);

Matcher matcher ;
public boolean checkPassword(String Strpassword){

    matcher=pattern.matcher(Strpassword);
       if(!matcher.matches()){
           return false;
       }
       return true;
}

}
