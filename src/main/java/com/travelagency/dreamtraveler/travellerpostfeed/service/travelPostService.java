package com.travelagency.dreamtraveler.travellerpostfeed.service;

import com.travelagency.dreamtraveler.travellerpostfeed.dto.TravelPostDTO;
import com.travelagency.dreamtraveler.travellerpostfeed.entity.TravelPost;

import java.util.List;

public interface travelPostService {

    public boolean addNewPost(TravelPostDTO travelPostDTO,String email) throws Exception;

    public boolean addUpdatePost(TravelPostDTO travelPostDTO) throws Exception;

    public List<TravelPostDTO> getAllPublicPost() throws Exception;
}
