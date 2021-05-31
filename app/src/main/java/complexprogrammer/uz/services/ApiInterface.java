package complexprogrammer.uz.services;



import java.util.List;

import complexprogrammer.uz.ui.news.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("Api/C0mplexApi/GetNews/")
    Call<List<NewsResponse>> getAllNews();
}
