package com.mohamed.movieticketsystem.repositories;

import com.mohamed.movieticketsystem.entities.MovieDetials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDetailsRepository extends CrudRepository<MovieDetials,Long> {
}
