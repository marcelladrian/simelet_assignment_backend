package com.example.simelet_assignment_backend.ui.model.response;

public class UsersResponse {

    private String userId;
    private String userName;
    private String name;
    private String email;
    private String noDevice;
    private String noHp;
    private String image;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoDevice() {
        return noDevice;
    }

    public void setNoDevice(String noDevice) {
        this.noDevice = noDevice;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
