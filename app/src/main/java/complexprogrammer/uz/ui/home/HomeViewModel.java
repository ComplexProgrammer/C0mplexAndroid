package complexprogrammer.uz.ui.home;

import java.io.Serializable;

public class HomeViewModel implements Serializable {
    public String title_uz;
    public String title_en;
    public String text_uz;
    public String text_en;
    public  String image_url;
    public String getTitle_uz() {
        return title_uz;
    }

    public void setTitle_uz(String title_uz) {
        this.title_uz = title_uz;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getText_uz() {
        return text_uz;
    }

    public void setText_uz(String text_uz) {
        this.text_uz = text_uz;
    }

    public String getText_en() {
        return text_en;
    }

    public void setText_en(String text_en) {
        this.text_en = text_en;
    }
    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
