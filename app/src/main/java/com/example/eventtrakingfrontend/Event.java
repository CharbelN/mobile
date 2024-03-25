package com.example.eventtrakingfrontend;

import java.util.List;

public class Event {
    private int eventId;
    private int userId;
    private String eventTitle;
    private String eventDateTime;
    private String addressName;
    private int image;
    private String description;
    private List<String> tags;

    // Constructor
    public Event(int eventId, int userId, String eventTitle, String eventDateTime, String addressName, int image, String description, List<String> tags) {
        this.eventId = eventId;
        this.userId = userId;
        this.eventTitle = eventTitle;
        this.eventDateTime = eventDateTime;
        this.addressName = addressName;
        this.image = image;
        this.description = description;
        this.tags = tags;
    }

    // Getters and setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
