package appinventiv.com.newsbuzz.data;

import appinventiv.com.newsbuzz.data.api.ApiManager;
import appinventiv.com.newsbuzz.model.home.NewsSource;
import appinventiv.com.newsbuzz.model.home.headline.NewsHeadline;
import retrofit2.Call;

/**
 * Created by admin1 on 1/5/18.
 */

public class DataManager implements IDataManager {

    private ApiManager apiManager;
    private static DataManager instance;

    private DataManager(){
        apiManager = ApiManager.getInstance();
    }
    public static DataManager getInstance(){
        if(instance == null){
            synchronized (DataManager.class){
                if(instance==null)
                    instance = new DataManager();
            }
        }
        return instance;
    }


    @Override
    public Call<NewsSource> getNewsSources() {
        return apiManager.getNewsSourcesFromApi();
    }

    @Override
    public Call<NewsHeadline> getTopHeadlines(String sourceName) {
        return apiManager.getTopHeadlinesFromApi(sourceName);
    }

    @Override
    public Call<NewsHeadline> getSearchedItem(String search) {
        return apiManager.getSearchedFromApi(search);
    }

    @Override
    public Call<NewsSource> getCategoriesNews(String category) {
        return apiManager.getCategories(category);
    }

}
