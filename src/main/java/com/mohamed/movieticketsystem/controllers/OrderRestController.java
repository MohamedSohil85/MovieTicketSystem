package com.mohamed.movieticketsystem.controllers;


import com.mohamed.movieticketsystem.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderRestController {
    private final OrdersRepository orderRepository;

    public OrderRestController(OrdersRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "/Orders",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List loadOrders() {
   return (List)orderRepository.findAll();
    }
    @RequestMapping(value = "/Order",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOrder(@Valid  @RequestParam("username")String username,@Valid @RequestParam("movieName")String movieName){
        return orderRepository.findByUser_UserNameAndMovie_MovieName(username, movieName).map(order -> {
            Date orderdate = new Date();
           order.setCreateOrders(orderdate);
            return new ResponseEntity<>(orderRepository.save(order), HttpStatus.CREATED);
        }).orElse(new ResponseEntity(HttpStatus.NO_CONTENT));
    }

    @RequestMapping(value = "/countOrdersByUsername/{username}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ResponseEntity countOrdersByusername(@Valid @PathVariable("username")String username){
       return new ResponseEntity(orderRepository.countOrdersByUser_Name(username),HttpStatus.OK);

    }


}
