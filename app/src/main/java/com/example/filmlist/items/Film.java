package com.example.filmlist.items;

public class Film implements Item {
    private String localized_name;
    private int type;
    private long id;

    public Film(String localized_name, int type, long id) {
        this.localized_name = localized_name;
        this.type = type;
        this.id = id;
    }

    public String getLocalizedName() {
        return localized_name;
    }

    public void setLocalizedName(String localized_name) {
        this.localized_name = localized_name;
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
