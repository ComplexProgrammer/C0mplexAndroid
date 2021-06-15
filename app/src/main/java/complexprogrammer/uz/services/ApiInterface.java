package complexprogrammer.uz.services;



import java.util.List;

import complexprogrammer.uz.models.TextValue;
import complexprogrammer.uz.ui.account.LoginViewModel;
import complexprogrammer.uz.ui.news.NewsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("Api/C0mplexApi/GetNews/")
    Call<List<NewsResponse>> getAllNews();

    @POST("Api/C0mplexApi/Login/")
    Call<TextValue> Login(@Body LoginViewModel model);
}
