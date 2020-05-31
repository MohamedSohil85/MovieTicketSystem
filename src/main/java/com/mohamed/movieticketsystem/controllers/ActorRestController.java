package com.mohamed.movieticketsystem.controllers;

import com.mohamed.movieticketsystem.entities.Actor;
import com.mohamed.movieticketsystem.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ActorRestController {

    final ActorRepository actorRepository;

    public ActorRestController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @RequestMapping(value = "/actors",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    ResponseEntity loadActors(){
        List<Actor>actors=(List)actorRepository.findAll();
        if(actors.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(actors,HttpStatus.OK);
    }
    @RequestMapping(value = "/actor",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity saveNewActor(@Valid @RequestBody Actor actor){
        return new ResponseEntity(actorRepository.save(actor),HttpStatus.CREATED);
    }
}
