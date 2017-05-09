package com.nikitaweb.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Никита on 05.04.2017.
 */
@Entity
@Table(name = "user_status", schema = "testschema")
public class UserStatusEntity {
    private String status;
    private int id;
    private List<UsersEntity> user;


    @OneToMany(mappedBy = "userStatus")
    public List<UsersEntity> getUsers(){
        return user;
    }
    public void setUsers(List<UsersEntity> users){
        this.user = users;
    }
    @Basic
    @Column(name = "status", columnDefinition = "String default User")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status == null){
            if (this.id ==1) this.status = "Admin";
            else this.status = "User";
        }
        this.status = status;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStatusEntity that = (UserStatusEntity) o;

        if (id != that.id) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
    @Override
    public String toString(){
        return status;
    }
}

