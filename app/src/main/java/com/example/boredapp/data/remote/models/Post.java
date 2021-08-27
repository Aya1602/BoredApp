package com.example.boredapp.data.remote.models;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("user")
    private int user;

    @SerializedName("group")
    private int group;

    public Post(String title, String content, int user, int group) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.group = group;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "title='" + title + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
