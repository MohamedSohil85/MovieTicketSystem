package com.mohamed.movieticketsystem.controllers;

import com.mohamed.movieticketsystem.entities.Actor;
import com.mohamed.movieticketsystem.repositories.ActorRepository;
import com.mohamed.movieticketsystem.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

public class ActorRestController {

    final ActorRepository actorRepository;
    final MovieRepository movieRepository;

    public ActorRestController(ActorRepository actorRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
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
@RequestMapping(value = "/changeActorById/{id}",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity updateActorById(@PathVariable("id")Long id,@Valid @RequestBody Actor newActor) {
    return actorRepository.findById(id).map(actor -> {
        actor.setActorName(newActor.getActorName());
        actor.setCountry(newActor.getCountry());
        return new ResponseEntity(actorRepository.save(actor), HttpStatus.CREATED);
    }).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
}
        @RequestMapping(value = "/saveMovieToActorById/{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
        ResponseEntity saveMovieToActor(@PathVariable("id")Long id,@RequestBody Actor actor) {
         return movieRepository.findById(id).map(movie -> {
             actor.setMovie(movie);
             return new ResponseEntity(actorRepository.save(actor),HttpStatus.CREATED);
         }).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
    

}
