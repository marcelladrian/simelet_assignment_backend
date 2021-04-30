package com.example.simelet_assignment_backend.io.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userTBL")
@SequenceGenerator(name = "seqUSR", initialValue =100, allocationSize = 10)
public class UsersEntity implements Serializable {
    private static final long serialVersionUID = 7784079425628975221L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUSR")
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)", length = 50, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true, columnDefinition = "VARCHAR(50)", length = 50)
    private String name;

    @Column(nullable = true,  unique = true)
    private String email;

    @Column(nullable = true)
    private String noDevice;

    @Column(nullable = true)
    private String noHp;

    @Column(nullable = true)
    private String image;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardEntity> cardEntity = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

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

    public List<CardEntity> getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(List<CardEntity> cardEntity) {
        this.cardEntity = cardEntity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() { return updateAt; }

    public void setUpdateAt(LocalDateTime updateAt) { this.updateAt = updateAt; }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
