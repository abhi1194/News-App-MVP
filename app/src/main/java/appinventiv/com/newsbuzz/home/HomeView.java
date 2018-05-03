package appinventiv.com.newsbuzz.home;

import appinventiv.com.newsbuzz.base.BaseView;
import appinventiv.com.newsbuzz.model.home.NewsSource;

/**
 * Created by admin1 on 1/5/18.
 */

public interface HomeView extends BaseView {

    void showNewsSources(NewsSource newsSource);
    void openHeadlineActivity(int pos);
    void showCategoryNews(NewsSource newsSource);
}
