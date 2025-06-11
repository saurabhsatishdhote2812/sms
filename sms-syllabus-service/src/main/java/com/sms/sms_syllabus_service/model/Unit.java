package com.sms.sms_syllabus_service.model;

import java.util.List;

public class Unit {
    private String title;
    private List<String> topics;

    // Constructors
    public Unit() {}
    public Unit(String title, List<String> topics) {
        this.title = title;
        this.topics = topics;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<String> getTopics() {
        return topics;
    }
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
