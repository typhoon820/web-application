package com.nikitaweb.model;

import javax.persistence.*;

/**
 * Created by Никита on 05.04.2017.
 */
@Entity
@Table(name = "authors", schema = "testschema")
public class AuthorsEntity {
    private int idAuthor;
    private String name;

    @Id
    @Column(name = "ID_author")
    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorsEntity that = (AuthorsEntity) o;

        if (idAuthor != that.idAuthor) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAuthor;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
