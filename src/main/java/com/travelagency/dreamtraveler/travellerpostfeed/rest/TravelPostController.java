package com.travelagency.dreamtraveler.travellerpostfeed.rest;


import com.travelagency.dreamtraveler.common.dto.ResponseDTO;
import com.travelagency.dreamtraveler.security.JwtUtil;
import com.travelagency.dreamtraveler.travellerpostfeed.dto.TravelPostDTO;
import com.travelagency.dreamtraveler.travellerpostfeed.service.travelPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/post")
public class TravelPostController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    travelPostService travelService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping(path="/newpost",consumes = "application/json", produces = "application/json")
    public ResponseDTO newPost(@RequestHeader(value="Authorization") String auth,@RequestBody TravelPostDTO model) {
        ResponseDTO responseDTO = new ResponseDTO();
        String jwt = auth.substring(7);
        String username = jwtUtil.extractUsername(jwt);

        try {
            boolean status = travelService.addNewPost(model,username);
            responseDTO.setSuccess(status);
            if(status)
                responseDTO.setMessage("post created successfully");
            else
                responseDTO.setMessage("could not save post");
        }
        catch (Exception e) {
            logger.error("error", e);
        }

        return responseDTO;
    }

    @GetMapping(path="/postfeed", produces = "application/json")
    public ResponseDTO postFeed() {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            List<TravelPostDTO> travelPostDTOS = travelService.getAllPublicPost();
            responseDTO.setSuccess(true);
            responseDTO.setMessage("post fetched successfully");
            responseDTO.setData(travelPostDTOS);

        }
        catch (Exception e) {
            responseDTO.setSuccess(false);
            logger.error("error", e);
        }

        return responseDTO;
    }
}
