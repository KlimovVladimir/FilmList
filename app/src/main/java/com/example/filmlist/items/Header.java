package com.example.filmlist.items;

public class Header implements Item {
    private String title;
    private int type;
    private long id;

    public Header(String title, int type, long id) {
        this.title = title;
        this.type = type;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
