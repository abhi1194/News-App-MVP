package appinventiv.com.newsbuzz.headlines;

import android.util.Log;

import appinventiv.com.newsbuzz.base.BaseModel;
import appinventiv.com.newsbuzz.model.FailureResponse;
import appinventiv.com.newsbuzz.model.home.headline.NewsHeadline;
import appinventiv.com.newsbuzz.network.NetworkResponse;

/**
 * Created by admin1 on 2/5/18.
 */

public class HeadlineModel extends BaseModel<HeadlineModelListener> {

    private static final String TAG = "HeadlineModel";

    public HeadlineModel(HeadlineModelListener listener){
        super(listener);
    }

    protected void getHeadlines(String source){
        getDataManager().getTopHeadlines(source).enqueue(new NetworkResponse<NewsHeadline>(this) {
            @Override
            public void onSuccess(NewsHeadline body) {
                getListener().onSourceHeadlinesFetched(body);
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

    protected void getSearchedNews(String search) {
        getDataManager().getSearchedItem(search).enqueue(new NetworkResponse<NewsHeadline>(this) {
            @Override
            public void onSuccess(NewsHeadline body) {
                getListener().onSearchNewsFetched(body);
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
