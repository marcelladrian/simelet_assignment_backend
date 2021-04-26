package com.example.simelet_assignment_backend.io.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userTBL")
@SequenceGenerator(name = "seqUSR", initialValue =100, allocationSize = 10)
public class UsersEntity implements Serializable {
    private static final long serialVersionUID = -8681946178871632226L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUSR")
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)", length = 50)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true, columnDefinition = "VARCHAR(50)", length = 50)
    private String name;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String noDevice;

    @Column(nullable = true)
    private String noHp;

    @Column(nullable = true)
    private String image;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
