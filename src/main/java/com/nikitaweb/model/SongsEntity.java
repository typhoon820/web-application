package com.nikitaweb.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Никита on 05.04.2017.
 */
@Entity
@Table(name = "songs", schema = "testschema")
public class SongsEntity {
    private String songName;
    private int idSong;
    private Integer downloadCount;
    private AuthorsEntity author;
    private List<UsersEntity> users;
    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name = "downloaded_songs",
            joinColumns = @JoinColumn(name = "ID_song"),
            inverseJoinColumns = @JoinColumn(name = "ID_user"))
    public List<UsersEntity> getUsers(){
        return users;
    }
    public void setUsers(List<UsersEntity> users){
        this.users = users;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_author")
    public AuthorsEntity getAuthor(){
        return author;
    }
    public void setAuthor(AuthorsEntity author){
        this.author = author;
    }


    @Basic
    @Column(name = "song_name")
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_song")
    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    @Basic
    @Column(name = "download_count")
    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongsEntity that = (SongsEntity) o;

        if (idSong != that.idSong) return false;
        if (songName != null ? !songName.equals(that.songName) : that.songName != null) return false;
        if (downloadCount != null ? !downloadCount.equals(that.downloadCount) : that.downloadCount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = songName != null ? songName.hashCode() : 0;
        result = 31 * result + idSong;
        result = 31 * result + (downloadCount != null ? downloadCount.hashCode() : 0);
        return result;
    }
}
