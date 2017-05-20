package com.nikitaweb.model;

import javax.persistence.*;

/**
 * Created by Никита on 05.04.2017.
 */
@Entity
@Table(name = "downloaded_songs", schema = "testschema")
@IdClass(DownloadedSongsEntityPK.class)
public class DownloadedSongsEntity {
    private int idSong;
    private int idUser;
    private String downloadTime;

    @Id
    @Column(name = "ID_song")
    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
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
    @Column(name = "download_time")
    public String getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(String downloadTime) {
        this.downloadTime = downloadTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DownloadedSongsEntity that = (DownloadedSongsEntity) o;

        if (idSong != that.idSong) return false;
        if (idUser != that.idUser) return false;
        if (downloadTime != null ? !downloadTime.equals(that.downloadTime) : that.downloadTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSong;
        result = 31 * result + idUser;
        result = 31 * result + (downloadTime != null ? downloadTime.hashCode() : 0);
        return result;
    }
}
