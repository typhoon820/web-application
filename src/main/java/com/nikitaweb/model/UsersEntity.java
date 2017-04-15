package com.nikitaweb.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Никита on 05.04.2017.
 */
@Entity
@Table(name = "users", schema = "testschema")
public class UsersEntity {
    private int idUser;
    private String login;
    private String password;
    private UserStatusEntity userStatus;

    private List<SongsEntity> songs;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "downloaded_songs",
            joinColumns = @JoinColumn(name = "ID_user"),
            inverseJoinColumns = @JoinColumn(name = "ID_song"))
    public List<SongsEntity> getSongs(){
        return songs;
    }
    public void setSongs(List<SongsEntity> songs){
        this.songs = songs;
    }


    @Id
    @Column(name = "ID_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    public UserStatusEntity getUserStatus(){
        return userStatus;
    }

    public void setUserStatus (UserStatusEntity userStatus){
        this.userStatus = userStatus;
    }

    @Override
    public String toString(){
        return String.format(
                "login=%s, status=%s",
                login,userStatus.getStatus()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (idUser != that.idUser) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
