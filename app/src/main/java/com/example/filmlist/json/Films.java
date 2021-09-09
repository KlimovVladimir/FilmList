package com.example.filmlist.json;

import com.example.filmlist.items.Film;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Films {

    @SerializedName("films")
    @Expose
    private List<Message> messages = null;

    public List<Message> getFilms() {
        return messages;
    }

    public void setFilms(List<Message> films) {
        this.messages = films;
    }

}