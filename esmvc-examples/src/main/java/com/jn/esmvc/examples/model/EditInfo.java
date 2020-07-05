package com.jn.esmvc.examples.model;

public class EditInfo{
    private static final long serialVersionUID = 1L;
    private String userId;
    private String userName;
    private long datetime;
    public EditInfo(String username, long datetime){
        this.userName = username;
        this.datetime = datetime;
    }

    public EditInfo() {
    }

    public EditInfo(String userId, String userName, long datetime) {
        this.userId = userId;
        this.userName = userName;
        this.datetime = datetime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
