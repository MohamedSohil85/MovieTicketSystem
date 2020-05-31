package com.mohamed.movieticketsystem.controllers;

import com.mohamed.movieticketsystem.entities.Movie;
import com.mohamed.movieticketsystem.exceptions.ResourcesException;
import com.mohamed.movieticketsystem.repositories.ActorRepository;
import com.mohamed.movieticketsystem.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
public class MovieRestController {
    final
    MovieRepository movieRepository;
    final ActorRepository actorRepository;

    public MovieRestController(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }


    @RequestMapping(value = "/getMovies",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMovies(){
        List<Movie> movieList=(List)movieRepository.findAll();
      if(movieList.isEmpty()){
          return new ResponseEntity(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity(movieList,HttpStatus.FOUND);
    }

//get Movie by max Rating
@RequestMapping(value = "/maxRatingMovie",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ResponseEntity getMaxrating(){
        List<Movie>movies=(List)movieRepository.findAll();
        Movie movie=movies.stream().max(Comparator.comparing(Movie::getRating)).get();
        if(movies.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(movie,HttpStatus.FOUND);
}
// get Movie by min Rating
@RequestMapping(value = "/minRatingMovie",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public Movie getMoviewithMinRating(){
    List<Movie>movies=(List)movieRepository.findAll();
    Movie movie=movies.stream().min(Comparator.comparing(Movie::getRating)).orElseThrow(NoSuchElementException::new);
    return movie;

}
// get list of old movies
    @RequestMapping(value = "/getOldMovies",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie>sortMovies(){
        List<Movie>movies=(List)movieRepository.findAll();
       return movies
               .stream()
               .sorted(Comparator.comparing(Movie::getReleaseDate))
               .collect(Collectors.toList());
    }
    @RequestMapping(value = "/Movie",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveNewMovie(@Valid  @RequestBody Movie movie){
        List<Movie>movies=(List)movieRepository.findAll();
        for(Movie temp:movies){
            if(temp.getMovieName().equalsIgnoreCase(movie.getMovieName())){
                return new ResponseEntity("Movie with this Name already found ",HttpStatus.FOUND);
            }
        }
        return new ResponseEntity(movieRepository.save(movie),HttpStatus.CREATED);
    }
    @RequestMapping(value = "/findMovieByKeyword/{keyword}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie>findMovieByKeyword(@PathVariable("keyword")String keyword){
        List<Movie>movies=(List)movieRepository.findAll();
return movies.stream().filter(movie -> movie.getMovieName().startsWith(keyword)).collect(Collectors.toList());
    }
    @RequestMapping(value = "/saveMovieToActor/{actorName}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity saveMovieToActor(@Valid @RequestBody Movie movie,@PathVariable("actorName") String actorName){
        return actorRepository.findActorByActorName(actorName).map(actor -> {
            actor.setMovie(movie);
            movie.getStarring().add(actor);
            return new ResponseEntity<>(movieRepository.save(movie),HttpStatus.CREATED);
        }).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
@RequestMapping(value = "/getMoviesByCountry/{country}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie>getMovieByCountry(@Valid @PathVariable("country")String country) throws ResourcesException {
     List<Movie>movies=(List)movieRepository.findAll();
     if(movies.isEmpty()){
        throw new ResourcesException("List is Emptey !");
     }
     return movieRepository.findMoviesByMovieDetials_Country(country);
}
}
