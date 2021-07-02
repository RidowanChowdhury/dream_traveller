package com.travelagency.dreamtraveler.registration.service;

import com.travelagency.dreamtraveler.registration.dto.UserDTO;
import com.travelagency.dreamtraveler.registration.entity.User;
import com.travelagency.dreamtraveler.registration.repository.registrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    registrationRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean createUser(UserDTO userDTO) throws Exception{
        boolean status = checkExist(userDTO.getEmail());
        if(status)
            return false;
        else{

            repository.save(convertToEntity(userDTO));
        }
        System.out.println(status+"05");
        return true;

    }

    @Override
    public boolean checkUserCreds(UserDTO userDTO) throws Exception {

//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());

        Optional<User> users  = repository.findByEmail(userDTO.getEmail());

        if (users.isPresent()){

            if (userDTO.getPassword().equals(users.get().getPassword())) {

                return true;
            }
        }
        return false;
    }

    private boolean checkExist(String email) {
        Optional<User> users  = repository.findByEmail(email);
        if (users.isPresent() ){
            return true;
        }
        return false;
    }

    private User convertToEntity(UserDTO userDto) throws ParseException {

        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
