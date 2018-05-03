package appinventiv.com.newsbuzz.data.api;

import java.util.HashMap;

import appinventiv.com.newsbuzz.model.home.NewsSource;
import appinventiv.com.newsbuzz.model.home.headline.NewsHeadline;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface ApiClient {

    String BASE_URL = "https://newsapi.org/v2/";


    @GET("sources")
    Call<NewsSource> getNewsSources(@QueryMap HashMap<String,String> map);

    @GET("top-headlines")
    Call<NewsHeadline> getTopHeadlines(@QueryMap HashMap<String,String> map);

}
