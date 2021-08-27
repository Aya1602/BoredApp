package com.example.boredapp.data.remote.models;

import com.google.gson.annotations.SerializedName;

public class Task {
    @SerializedName("activity")
    private String task;

    @SerializedName("accessibility")
    private Double accessibility;

    @SerializedName("type")
    private String type;

    @SerializedName("participants")
    private String participants;

    @SerializedName("price")
    private Double price;

    @SerializedName("key")
    private Integer key;

    public Task(String task, Double accessibility, String type, String participants, Double price, Integer key) {
        this.task = task;
        this.accessibility = accessibility;
        this.type = type;
        this.participants = participants;
        this.price = price;
        this.key = key;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Double getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Double accessibility) {
        this.accessibility = accessibility;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
