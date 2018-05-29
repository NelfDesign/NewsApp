package fr.nelfdesign.oc_news_reader;

import android.graphics.Bitmap;

public class RssItem {

    private String link;
    private String title;
    private String description;
    private String datePublication;
    private String image;
    private String guid;

    public RssItem(String link, String title, String description, String datePublication,String image, String guid) {
        this.link = link;
        this.title = title;
        this.description = description;
        this.datePublication = datePublication;
        this.image = image;
        this.guid = guid;
    }

    public RssItem(){}

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
