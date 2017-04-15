package com.nikitaweb.model;

import javax.persistence.*;

/**
 * Created by Никита on 05.04.2017.
 */
@Entity
@Table(name = "user_status", schema = "testschema")
public class UserStatusEntity {
    private String status;
    private int statusId;
    private UsersEntity user;


    @OneToOne
    @JoinColumn(name = "status_id")
    public UsersEntity getUser(){
        return user;
    }
    public void setUser(UsersEntity user){
        this.user = user;
    }
    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Id
    @Column(name = "status_id")
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStatusEntity that = (UserStatusEntity) o;

        if (statusId != that.statusId) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + statusId;
        return result;
    }
}

