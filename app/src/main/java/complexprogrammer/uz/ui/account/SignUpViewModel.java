package complexprogrammer.uz.ui.account;

import com.google.gson.annotations.SerializedName;

public class SignUpViewModel {
    @SerializedName("FirstName")
    public String first_name;
    @SerializedName("LastName")
    public String last_name;
    @SerializedName("MiddleName")
    public String middle_name;
    @SerializedName("Email")
    public String email;
    @SerializedName("Password")
    public String password;
}
