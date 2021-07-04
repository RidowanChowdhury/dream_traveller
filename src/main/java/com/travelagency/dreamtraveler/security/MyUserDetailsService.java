package com.travelagency.dreamtraveler.security;

import com.travelagency.dreamtraveler.registration.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.travelagency.dreamtraveler.registration.service.RegistrationService;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    RegistrationService regService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            UserDTO userdto = regService.getUserbyEmail(s);
            return new User(userdto.getEmail(), userdto.getPassword(),
                    new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}