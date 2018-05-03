package appinventiv.com.newsbuzz.home;

import appinventiv.com.newsbuzz.base.BaseModelListener;
import appinventiv.com.newsbuzz.model.home.NewsSource;

/**
 * Created by admin1 on 1/5/18.
 */

public interface HomeModelListener extends BaseModelListener {
    void onNewsSourcesFetched(NewsSource newsSource);
    void onCategoryNewsFetched(NewsSource source);
}
