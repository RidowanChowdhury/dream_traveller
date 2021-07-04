package com.travelagency.dreamtraveler.registration.rest;


import com.travelagency.dreamtraveler.common.dto.ResponseDTO;
import com.travelagency.dreamtraveler.registration.dto.UserDTO;
import com.travelagency.dreamtraveler.registration.service.RegistrationService;
import com.travelagency.dreamtraveler.security.JwtUtil;
import com.travelagency.dreamtraveler.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/registration")
public class registrationController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    RegistrationService registrationService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping(path="/newuser",consumes = "application/json", produces = "application/json")
    public ResponseDTO registration(@RequestHeader(value="Authorization") String auth,@RequestBody UserDTO model) {
        ResponseDTO responseDTO = new ResponseDTO();
        String jwt = auth.substring(7);

        String username = jwtUtil.extractUsername(jwt);


        try {
            boolean status = registrationService.createUser(model);
            responseDTO.setSuccess(status);
            if(status)
                responseDTO.setMessage("user created successfully");
            else
                responseDTO.setMessage("user exists");
        }
        catch (Exception e) {
            logger.error("error", e);
        }

        return responseDTO;
    }

    @PostMapping(path="/login",consumes = "application/json", produces = "application/json")
    public ResponseDTO login(@RequestBody UserDTO model) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    model.getEmail(),model.getPassword()
            ));
            final UserDetails userDetails = myUserDetailsService.loadUserByUsername(model.getEmail());
            String token = jwtUtil.generateToken(userDetails);

            responseDTO.setSuccess(true);
            responseDTO.setMessage("user login successful");
            responseDTO.setJwt(token);
        }
        catch (Exception e) {
            responseDTO.setMessage("user login failed");
            responseDTO.setSuccess(false);
            logger.error("error", e);
        }

        return responseDTO;
    }

}
