package com.travelagency.dreamtraveler.registration.service;

import com.travelagency.dreamtraveler.registration.dto.UserDTO;
import com.travelagency.dreamtraveler.registration.entity.User;

public interface RegistrationService {

    public boolean createUser(UserDTO userDTO) throws Exception;

    public boolean checkUserCreds(UserDTO userDTO) throws Exception;

    public UserDTO getUserbyEmail(String email) throws Exception;


}
