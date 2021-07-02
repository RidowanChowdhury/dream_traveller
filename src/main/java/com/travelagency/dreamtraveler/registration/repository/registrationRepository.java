package com.travelagency.dreamtraveler.registration.repository;

import com.travelagency.dreamtraveler.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface registrationRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String aLong);


}
