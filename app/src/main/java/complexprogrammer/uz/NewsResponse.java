package complexprogrammer.uz;

import java.io.Serializable;
import java.util.Date;

public class NewsResponse implements Serializable {
    private int id;
    private int user_id;
    private String guid;
    private String short_title_uz;
    private String short_title_en;
    private String long_title_uz;
    private String long_title_en;
    private String text_uz;
    private String text_en;
    private String image_url;
    private int sort_number;
    private int view_count;
    private String print;
    private String best_print;
    private String reg_date;
    private String change_date;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getShort_title_uz() {
        return short_title_uz;
    }

    public void setShort_title_uz(String short_title_uz) {
        this.short_title_uz = short_title_uz;
    }

    public String getShort_title_en() {
        return short_title_en;
    }

    public void setShort_title_en(String short_title_en) {
        this.short_title_en = short_title_en;
    }

    public String getLong_title_uz() {
        return long_title_uz;
    }

    public void setLong_title_uz(String long_title_uz) {
        this.long_title_uz = long_title_uz;
    }

    public String getLong_title_en() {
        return long_title_en;
    }

    public void setLong_title_en(String long_title_en) {
        this.long_title_en = long_title_en;
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

    public int getSort_number() {
        return sort_number;
    }

    public void setSort_number(int sort_number) {
        this.sort_number = sort_number;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public String getBest_print() {
        return best_print;
    }

    public void setBest_print(String best_print) {
        this.best_print = best_print;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public void setChange_date(String change_date) {
        this.change_date = change_date;
    }
}
