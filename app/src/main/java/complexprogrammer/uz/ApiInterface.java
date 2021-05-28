package complexprogrammer.uz;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("Api/C0mplexApi/GetNews/")
    Call<List<NewsResponse>> getAllNews();
}
