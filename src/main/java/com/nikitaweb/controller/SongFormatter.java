package com.nikitaweb.controller;

import com.nikitaweb.model.AuthorsEntity;
import com.nikitaweb.model.SongsEntity;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.util.Locale;

@Controller
public class SongFormatter implements Formatter<SongsEntity> {

    @Override
    public SongsEntity parse(String s, Locale locale) throws ParseException {
        SongsEntity res = new SongsEntity();
        AuthorsEntity author = new AuthorsEntity();
        String[] data = s.split("_");
        res.setIdSong(Integer.parseInt(data[0]));
        res.setSongName(data[1]);
        author.setIdAuthor(Integer.parseInt(data[2]));
        author.setName(data[3]);
        res.setAuthor(author);
        res.setDownloadCount(0);
        return res;
    }

    @Override
    public String print(SongsEntity songsEntity, Locale locale) {
        String res;
        res = String.valueOf(songsEntity.getIdSong())+"_"
                +songsEntity.getSongName()+"_"
                +String.valueOf(songsEntity.getAuthor().getIdAuthor())+"_"
                +songsEntity.getAuthor().getName();
        return res;
    }
}
