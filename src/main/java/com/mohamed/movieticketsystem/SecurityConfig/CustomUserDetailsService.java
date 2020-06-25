package com.mohamed.movieticketsystem.SecurityConfig;

import com.mohamed.movieticketsystem.entities.RegistretedUser;
import com.mohamed.movieticketsystem.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        RegistretedUser user=userRepository.findRegistretedUserByUserName(s);
        CustomUserDetails userDetails = null;

        if(user !=null){
            userDetails=new CustomUserDetails();
            userDetails.setUser(user);
        }else {
            throw new UsernameNotFoundException("User Object not found !");
        }
        return userDetails;
    }
}
