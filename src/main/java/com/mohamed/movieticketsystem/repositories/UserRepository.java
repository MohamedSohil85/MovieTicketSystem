package com.mohamed.movieticketsystem.repositories;

import com.mohamed.movieticketsystem.entities.RegistretedUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<RegistretedUser,Long> {
     Optional<RegistretedUser>findRegistretedUserByName(String name);
    Optional<RegistretedUser>findRegistretedUserByUserNameAndPassword(String username,String password);
    RegistretedUser findRegistretedUserByUserName(String username);
}
