package com.travelagency.dreamtraveler.registration.service;

import com.travelagency.dreamtraveler.registration.dto.UserDTO;
import com.travelagency.dreamtraveler.registration.entity.User;
import com.travelagency.dreamtraveler.registration.repository.registrationRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    registrationRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean createUser(UserDTO userDTO) throws Exception{
        try {
            boolean status = checkExist(userDTO.getEmail());
            if (status)
                return false;
            else {
                repository.save(convertToEntity(userDTO));
                return true;
            }
        }
        catch (Exception e) {
            logger.error("error", e);
            return false;
        }

    }

    @Override
    public boolean checkUserCreds(UserDTO userDTO) throws Exception {
        try {
            Optional<User> users = repository.findByEmail(userDTO.getEmail());

            return users.filter(user -> userDTO.getPassword().equals(user.getPassword())).isPresent();
        }
        catch (Exception e) {
            logger.error("error", e);
            throw new Exception();
        }
    }

    @Override
    public UserDTO getUserbyEmail(String email) throws Exception {
        try {
            Optional<User> user = repository.findByEmail(email);
            if (user.isPresent()) {
                return convertToDto(user.get());
            }
        }
        catch (Exception e) {
            logger.error("error", e);
            throw new Exception();
        }
        return null;
    }

    private boolean checkExist(String email) {
        try {
            Optional<User> users = repository.findByEmail(email);
            if (users.isPresent()) {
                return true;
            }
        }
        catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    private User convertToEntity(UserDTO userDto) throws ParseException {

        return modelMapper.map(userDto, User.class);
    }

    private UserDTO convertToDto(User user) throws ParseException {

        return modelMapper.map(user, UserDTO.class);
    }
}
