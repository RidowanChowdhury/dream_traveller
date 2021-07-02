package com.travelagency.dreamtraveler.registration.service;

import com.travelagency.dreamtraveler.registration.dto.UserDTO;

public interface RegistrationService {

    public boolean createUser(UserDTO userDTO) throws Exception;

    public boolean checkUserCreds(UserDTO userDTO) throws Exception;


}
