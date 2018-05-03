package appinventiv.com.newsbuzz.data.api;

import java.util.HashMap;

import appinventiv.com.newsbuzz.model.home.NewsSource;
import appinventiv.com.newsbuzz.model.home.headline.NewsHeadline;
import appinventiv.com.newsbuzz.utils.AppConstant;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin1 on 1/5/18.
 */

public class ApiManager {

    private static final ApiManager instance = new ApiManager();
    private static final String API_KEY = "b6c2d1f76abd4142b60eb0a5fac3559b";

    private final ApiClient apiClient;

    private ApiManager() {
        apiClient = getRetrofitService();
    }
    public static ApiManager getInstance(){
        return instance;
    }

    private static ApiClient getRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiClient.BASE_URL)
                .build();

        return retrofit.create(ApiClient.class);
    }

    public Call<NewsSource> getNewsSourcesFromApi() {
        HashMap<String,String> params = new HashMap<>();
        params.put(AppConstant.API_KEY,API_KEY);
        return apiClient.getNewsSources(params);
    }

    public Call<NewsHeadline> getTopHeadlinesFromApi(String source){
        HashMap<String,String> params = new HashMap<>();
        params.put(AppConstant.SOURCE_KEY,source);
        params.put(AppConstant.API_KEY,API_KEY);
        return apiClient.getTopHeadlines(params);
    }

    public Call<NewsHeadline> getSearchedFromApi(String search) {
        HashMap<String,String> params = new HashMap<>();
        params.put(AppConstant.SEARCH_KEY,search);
        params.put(AppConstant.API_KEY,API_KEY);
        return apiClient.getTopHeadlines(params);
    }

    public Call<NewsSource> getCategories(String category){
        HashMap<String,String> params = new HashMap<>();
        params.put(AppConstant.CATEGORY_KEY,category);
        params.put(AppConstant.API_KEY,API_KEY);
        return apiClient.getNewsSources(params);
    }
}
