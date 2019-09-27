package com.nwafu.PISMDB.entity;

import java.util.List;

public class BrowsePathways {
    Pic pic;
    List<Pictures>pictures;

    public Pic getPic() {
        return pic;
    }

    public void setPic(Pic pic) {
        this.pic = pic;
    }

    public List<Pictures> getPictures() {
        return pictures;
    }

    public void setPictures(List<Pictures> pictures) {
        this.pictures = pictures;
    }
}
