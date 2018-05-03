package appinventiv.com.newsbuzz.home;

import android.util.Log;

import appinventiv.com.newsbuzz.base.BaseModel;
import appinventiv.com.newsbuzz.model.FailureResponse;
import appinventiv.com.newsbuzz.model.home.NewsSource;
import appinventiv.com.newsbuzz.network.NetworkResponse;

/**
 * Created by admin1 on 1/5/18.
 */

public class HomeModel extends BaseModel<HomeModelListener> {

    private static final String TAG = "HomeModel";
    public HomeModel(HomeModelListener listener){
        super(listener);
    }


    protected void getNews(){
        getDataManager().getNewsSources().enqueue(new NetworkResponse<NewsSource>(this) {
            @Override
            public void onSuccess(NewsSource body) {
                getListener().onNewsSourcesFetched(body);


            }

            @Override
            public void onFailure(int code, FailureResponse failureResponse) {
                Log.e(TAG, "onFailure: " + failureResponse.getMsg());
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "onError: ", t);

            }
        });

    }

    protected void getCategoryNews(String category) {
        getDataManager().getCategoriesNews(category).enqueue(new NetworkResponse<NewsSource>(this) {
            @Override
            public void onSuccess(NewsSource body) {
                getListener().onCategoryNewsFetched(body);
            }

            @Override
            public void onFailure(int code, FailureResponse failureResponse) {
                Log.e(TAG, "onFailure: " + failureResponse.getMsg());
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "onError: ", t);
            }
        });
    }
}
