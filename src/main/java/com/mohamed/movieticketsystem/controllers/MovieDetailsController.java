package com.mohamed.movieticketsystem.controllers;

import com.mohamed.movieticketsystem.entities.MovieDetials;
import com.mohamed.movieticketsystem.repositories.MovieDetailsRepository;
import com.mohamed.movieticketsystem.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MovieDetailsController {
    final MovieDetailsRepository movieDetailsRepository;
        final MovieRepository movieRepository;

    public MovieDetailsController(MovieDetailsRepository movieDetailsRepository, MovieRepository movieRepository) {
        this.movieDetailsRepository = movieDetailsRepository;
        this.movieRepository = movieRepository;
    }


    @RequestMapping(value = "/saveMovieDetailsToMovie/{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveMoviedetials(@Valid @RequestBody MovieDetials movieDetials, @Valid @PathVariable("id")Long id){
        return movieRepository.findById(id).map(movie -> {
           movie.setMovieDetials(movieDetials);
            movieDetials.setMovie(movie);
            return new ResponseEntity(movieDetailsRepository.save(movieDetials), HttpStatus.CREATED);
        }).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

}
