package com.nikitaweb.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Никита on 05.04.2017.
 */
@Entity
@Table(name = "downloaded_songs", schema = "testschema")

public class DownloadedSongsEntity {

    private Date downloadTime;

    private SongsEntity song;
    private UsersEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "ID_song")
    public SongsEntity getSong(){
        return song;
    }
    public void setSong(SongsEntity song){
        this.song = song;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_user")
    public UsersEntity getUser(){
        return user;
    }
    public void setUser(UsersEntity user){
        this.user = user;
    }

    @EmbeddedId
    private DownloadedSongsEntityPK pkey;

    public DownloadedSongsEntityPK getPkey() {
        return pkey;
    }

    public void setPkey(DownloadedSongsEntityPK pkey) {
        this.pkey = pkey;
    }


    @Basic
    @Column(name = "download_time")
    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }


}
