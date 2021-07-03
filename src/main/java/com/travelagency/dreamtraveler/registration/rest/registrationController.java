package com.travelagency.dreamtraveler.registration.rest;


import com.travelagency.dreamtraveler.common.dto.ResponseDTO;
import com.travelagency.dreamtraveler.registration.dto.UserDTO;
import com.travelagency.dreamtraveler.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/registration")
public class registrationController {

    @Autowired
    RegistrationService registrationService;


    @PostMapping(path="/newuser",consumes = "application/json", produces = "application/json")
    public ResponseDTO registration(@RequestBody UserDTO model) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            boolean status = registrationService.createUser(model);
            responseDTO.setSuccess(status);
            if(status)
                responseDTO.setMessage("user created successfully");
            else
                responseDTO.setMessage("user exists");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("yo"+responseDTO.getMessage());
        return responseDTO;
    }

    @PostMapping(path="/login",consumes = "application/json", produces = "application/json")
    public ResponseDTO login(@RequestBody UserDTO model) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            boolean status = registrationService.checkUserCreds(model);
            responseDTO.setSuccess(status);
            if(status)
                responseDTO.setMessage("user login successful");
            else
                responseDTO.setMessage("user login failed");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("yo"+responseDTO.getMessage());
        return responseDTO;
    }

}
