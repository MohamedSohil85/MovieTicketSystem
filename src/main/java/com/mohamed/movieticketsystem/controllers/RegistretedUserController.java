package com.mohamed.movieticketsystem.controllers;

import com.mohamed.movieticketsystem.entities.PasswordValidation;
import com.mohamed.movieticketsystem.entities.RegistretedUser;

import com.mohamed.movieticketsystem.exceptions.ResourcesException;
import com.mohamed.movieticketsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class RegistretedUserController {

    final UserRepository userRepository;

    public RegistretedUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List getUsers() throws ResourcesException {
        List<RegistretedUser> users = (List) userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourcesException("Users not found !");
        }
        return users;
    }

    @RequestMapping(value = "/saveNewUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveNewUser(@Valid @RequestBody RegistretedUser user) {
        PasswordValidation passwordValidation = new PasswordValidation();
        List<RegistretedUser> users = (List) userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equalsIgnoreCase(user.getName()))
                if (users.get(i).getUserName().equals(user.getUserName()))
                    return new ResponseEntity(HttpStatus.FOUND);
        }

        if (!passwordValidation.checkPassword(user.getPassword())) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(userRepository.save(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUserName(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

    @RequestMapping(value = "/deleteByName/{name}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistretedUser> deleteUserByName(@Valid @PathVariable("name")String name)throws ResourcesException{
       return userRepository.findRegistretedUserByName(name).map(user -> {
           userRepository.delete(user);
           return new ResponseEntity("User has been deleted",HttpStatus.OK);
       }).orElseThrow(()->new ResourcesException("User not found !"));
    }
}