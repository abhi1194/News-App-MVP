package appinventiv.com.newsbuzz.data;

import appinventiv.com.newsbuzz.model.home.NewsSource;
import appinventiv.com.newsbuzz.model.home.headline.NewsHeadline;
import retrofit2.Call;

/**
 * Created by admin1 on 1/5/18.
 */

public interface IDataManager {

    Call<NewsSource> getNewsSources();
    Call<NewsHeadline> getTopHeadlines(String sourceName);
    Call<NewsHeadline> getSearchedItem(String search);
    Call<NewsSource> getCategoriesNews(String category);

}
