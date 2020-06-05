package com.mohamed.movieticketsystem.controllers;

import com.mohamed.movieticketsystem.entities.OrderDetails;
import com.mohamed.movieticketsystem.repositories.MovieRepository;
import com.mohamed.movieticketsystem.repositories.OrderDetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OrderDetailsRestController {
    private final OrderDetailsRepository detailsRepository;
    private final MovieRepository movieRepository;

    public OrderDetailsRestController(OrderDetailsRepository detailsRepository, MovieRepository movieRepository) {
        this.detailsRepository = detailsRepository;
        this.movieRepository = movieRepository;
    }

    @RequestMapping(value = "/saveMovieToShoppingCart/{movieName}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    ResponseEntity saveMovieToCart(@Valid @PathVariable("movieName")String movieName, @RequestBody OrderDetails orderDetails){
        return movieRepository.findMovieByMovieName(movieName).map(movie -> {
            orderDetails.setMovie(movie);
            return new ResponseEntity(detailsRepository.save(orderDetails), HttpStatus.CREATED);
        }).orElse(new ResponseEntity(HttpStatus.NO_CONTENT));
    }
}
