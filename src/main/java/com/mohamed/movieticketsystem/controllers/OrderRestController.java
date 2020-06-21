package com.mohamed.movieticketsystem.controllers;


import com.mohamed.movieticketsystem.entities.Orders;
import com.mohamed.movieticketsystem.entities.RegistretedUser;
import com.mohamed.movieticketsystem.exceptions.ResourcesException;
import com.mohamed.movieticketsystem.repositories.MovieRepository;
import com.mohamed.movieticketsystem.repositories.OrdersRepository;
import com.mohamed.movieticketsystem.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
public class OrderRestController {
    private final OrdersRepository orderRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    public OrderRestController(OrdersRepository orderRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;

        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/Orders",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List loadOrders() {
   return (List)orderRepository.findAll();
    }


@RequestMapping(value = "/deleteOrders",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.DELETE)
    void deleteOrders() throws ResourcesException {
        List<Orders>orders=(List)orderRepository.findAll();
        if(orders.isEmpty()){
            throw new  ResourcesException("the List is empty !");
        }
        orderRepository.deleteAll();
}
@RequestMapping(value = "/createOrderByMovieId/{movieId}/{userId}/Order",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
  ResponseEntity createOrder( @PathVariable("movieId")Long id,@PathVariable("userId")Long userid, @RequestBody Orders orders){
        return movieRepository.findById(id).map(movie -> {
            Optional<RegistretedUser>user=userRepository.findById(userid);
            orders.setUser(user.get());
            orders.setCreateOrders(new Date());
            orders.setMovie(movie);
            orders.setTotalPrice(orders.getQuantity()*movie.getPrice());
            return new ResponseEntity(orderRepository.save(orders),HttpStatus.CREATED);
        }).orElse(new ResponseEntity(HttpStatus.NO_CONTENT));
}


}
