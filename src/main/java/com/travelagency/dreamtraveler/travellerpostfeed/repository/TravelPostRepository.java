package com.travelagency.dreamtraveler.travellerpostfeed.repository;

import com.travelagency.dreamtraveler.registration.entity.User;
import com.travelagency.dreamtraveler.travellerpostfeed.entity.TravelPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelPostRepository extends JpaRepository<TravelPost,Long> {

    List<TravelPost> findByPrivacy(String privacy);
}
