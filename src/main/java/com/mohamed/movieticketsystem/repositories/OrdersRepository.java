package com.mohamed.movieticketsystem.repositories;

import com.mohamed.movieticketsystem.entities.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends CrudRepository<Orders,Long> {
   // @Query(value = "select * from Orders where Orders.user.userName =:username and Orders.movie.movieName =:movieName",nativeQuery = true)
    Optional<Orders>findByUser_UserNameAndMovie_MovieName(String username,String movieName);
    public  int countOrdersByUser_Name(String username);
   // @Query(value = "select * from Orders where Orders.movie.movieName =: moviename and Orders.user.userName =:username",nativeQuery = true)
    Optional <Orders>findByUserNameAndMovie_MovieName(String username,String moviename);
    
}
