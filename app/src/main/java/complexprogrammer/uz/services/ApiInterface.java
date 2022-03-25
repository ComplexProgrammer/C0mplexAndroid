package complexprogrammer.uz.services;


import java.util.List;

import complexprogrammer.uz.models.TextValue;
import complexprogrammer.uz.ui.account.LoginViewModel;
import complexprogrammer.uz.ui.account.SignUpViewModel;
import complexprogrammer.uz.ui.account.UserResponse;
import complexprogrammer.uz.ui.news.NewsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("GetSavol/")
    Call<List<NewsResponse>> getAllNews();

    @POST("Api/C0mplexApi/Login/")
    Call<TextValue> Login(@Body LoginViewModel model);

    @POST("Api/C0mplexApi/SignOut/")
    Call<TextValue> SignOut(@Query("user_id") int user_id);

    @POST("Api/C0mplexApi/Register/")
    Call<TextValue> Register(@Body SignUpViewModel model);

    @GET("Api/C0mplexApi/GetUserById")
    Call<UserResponse> GetUserById(@Query("user_id") int user_id);

}
