package com.travelagency.dreamtraveler.travellerpostfeed.entity;

import javax.persistence.*;

@Entity
@Table(name = "travel_post")
public class Travel_Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false, length = 45)
    private String post;

    @Column(nullable = false, unique = false, length = 45)
    private Long userId;

    @Column(nullable = false, unique = false, length = 45)
    private String userName;

    @Column(nullable = false, unique = false, length = 45)
    private String location;

    @Column(nullable = false, unique = false, length = 45)
    private String privacy;

    public Long getId() {
        return id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }
}
