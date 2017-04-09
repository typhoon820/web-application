package com.nikitaweb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Никита on 05.04.2017.
 */
public class DownloadedSongsEntityPK implements Serializable {
    private int idSong;
    private int idUser;

    @Column(name = "ID_song")
    @Id
    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    @Column(name = "ID_user")
    @Id
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DownloadedSongsEntityPK that = (DownloadedSongsEntityPK) o;

        if (idSong != that.idSong) return false;
        if (idUser != that.idUser) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSong;
        result = 31 * result + idUser;
        return result;
    }
}
