package com.mohamed.movieticketsystem.controllers;


import com.mohamed.movieticketsystem.entities.Orders;
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
    private final UserRepository userRepository;
    public OrderRestController(OrdersRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/Orders",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List loadOrders() {
   return (List)orderRepository.findAll();
    }
    @RequestMapping(value = "/saveToUserByName/{username}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    ResponseEntity saveUsertoOrder(@Valid @PathVariable("username")String username, @RequestBody Orders orders){
        return userRepository.findRegistretedUserByName(username).map(user -> {
            orders.setCreateOrders(new Date());
            user.getOrdersList().add(orders);
            orders.setUser(user);
            return new ResponseEntity(orderRepository.save(orders), HttpStatus.CREATED);
        }).orElse(new ResponseEntity(HttpStatus.NO_CONTENT));
    }




}
