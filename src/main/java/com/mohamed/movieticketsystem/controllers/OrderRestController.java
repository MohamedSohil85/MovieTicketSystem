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

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class OrderRestController {
    private final OrdersRepository orderRepository;
    private final MovieRepository movieRepository;
    public OrderRestController(OrdersRepository orderRepository, MovieRepository movieRepository) {
        this.orderRepository = orderRepository;

        this.movieRepository = movieRepository;
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
@RequestMapping(value = "/createOrderByMovieId/{id}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
  ResponseEntity createOrder( @PathVariable("id")Long id, @RequestBody Orders orders){
        return movieRepository.findById(id).map(movie -> {

            orders.setCreateOrders(new Date());
            movie.setOrders(orders);
            return new ResponseEntity(orderRepository.save(orders),HttpStatus.CREATED);
        }).orElse(new ResponseEntity(HttpStatus.NO_CONTENT));
}
    @RequestMapping(value = "/addOrderToUserById/{orderId}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    ResponseEntity addOrderToUser(@PathVariable("orderId")Long orderId, @RequestBody RegistretedUser user){
        return orderRepository.findById(orderId).map(orders -> {
            user.getOrdersList().add(orders);
            orders.setUser(user);
            return new ResponseEntity(orderRepository.save(orders),HttpStatus.CREATED);
        }).orElse(new ResponseEntity(HttpStatus.NO_CONTENT));
    }
}
