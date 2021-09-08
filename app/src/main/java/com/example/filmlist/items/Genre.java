package com.example.filmlist.items;

public class Genre implements Item {
    private String genre;
    private int type;
    private long id;

    public Genre(String genre, int type, long id) {
        this.genre = genre;
        this.type = type;
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int getItemType() {
        return type;
    }

    @Override
    public long getID() {
        return id;
    }
}
