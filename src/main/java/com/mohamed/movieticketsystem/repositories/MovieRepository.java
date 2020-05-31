package com.mohamed.movieticketsystem.repositories;

import com.mohamed.movieticketsystem.entities.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie,Long> {
    Optional<Movie>findMovieByMovieName(String name);
    List<Movie>findMoviesByReleaseDate(Date releasedate);
    List<Movie>findMoviesByStarring(String actorName);
    List<Movie>findMoviesByMovieDetials_Country(String country);
}
