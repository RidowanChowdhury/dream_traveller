package com.travelagency.dreamtraveler.travellerpostfeed.service;

import com.travelagency.dreamtraveler.registration.dto.UserDTO;
import com.travelagency.dreamtraveler.registration.entity.User;
import com.travelagency.dreamtraveler.registration.repository.registrationRepository;
import com.travelagency.dreamtraveler.travellerpostfeed.dto.TravelPostDTO;
import com.travelagency.dreamtraveler.travellerpostfeed.entity.TravelPost;
import com.travelagency.dreamtraveler.travellerpostfeed.repository.TravelPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class travelPostServiceImpl implements travelPostService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    TravelPostRepository travelPostRepository;

    @Autowired
    registrationRepository regrepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean addNewPost(TravelPostDTO travelPostDTO, String email) throws Exception {
        try {
            Optional<User> user = regrepository.findByEmail(email);
            if (user.isPresent()) {
                travelPostDTO.setUserId(user.get().getId());
                travelPostDTO.setUserName(user.get().getFirstName() + " " + user.get().getLastName());
                travelPostRepository.save(convertToEntity(travelPostDTO));
                return true;
            }
        }
        catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return false;
    }

    @Override
    public boolean addUpdatePost(TravelPostDTO travelPostDTO) throws Exception {
        try {
            travelPostRepository.save(convertToEntity(travelPostDTO));
            return true;
        }
        catch (Exception e) {
            logger.error("error", e);
            return false;
        }

    }

    @Override
    public  List<TravelPostDTO> getAllPublicPost() throws Exception {
        try {
            List<TravelPost> travelPostList = travelPostRepository.findByPrivacy("public");
            if (travelPostList.size() > 0) {
                return convertToDtoList(travelPostList);
            }
        }
        catch (Exception e) {
            logger.error("error", e);
        }
        return null ;

    }

    private TravelPost convertToEntity(TravelPostDTO travelPostDTO) throws ParseException {

        return modelMapper.map(travelPostDTO, TravelPost.class);
    }

    private TravelPostDTO convertToDto(TravelPost travelPost) throws ParseException {

        return modelMapper.map(travelPost, TravelPostDTO.class);
    }

    private List<TravelPostDTO> convertToDtoList(List<TravelPost> travelPostList) throws ParseException {
        List<TravelPostDTO> travelPostDTOS = new ArrayList<TravelPostDTO>();
        try {
            for (TravelPost temp : travelPostList) {
                System.out.println(travelPostList.size());
                TravelPostDTO travelPostDTO = modelMapper.map(temp, TravelPostDTO.class);
                travelPostDTOS.add(travelPostDTO);
            }
        }
        catch (Exception e) {
            logger.error("error", e);
        }

        return travelPostDTOS;
    }
}
