package complexprogrammer.uz.ui.account;

import java.io.Serializable;

public class UserResponse implements Serializable {
    private int id;
    private String guid;
    private String role;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String email;
    private String phone_number;
    private String login;
    private String password;
    private String visit_count;
    private String reg_date;
    private String change_date;
    private String last_access_date;
    private String last_release_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVisit_count() {
        return visit_count;
    }

    public void setVisit_count(String visit_count) {
        this.visit_count = visit_count;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getChange_date() {
        return change_date;
    }

    public void setChange_date(String change_date) {
        this.change_date = change_date;
    }

    public String getLast_access_date() {
        return last_access_date;
    }

    public void setLast_access_date(String last_access_date) {
        this.last_access_date = last_access_date;
    }

    public String getLast_release_date() {
        return last_release_date;
    }

    public void setLast_release_date(String last_release_date) {
        this.last_release_date = last_release_date;
    }
}
