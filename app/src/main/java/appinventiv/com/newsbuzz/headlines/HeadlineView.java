package appinventiv.com.newsbuzz.headlines;

import appinventiv.com.newsbuzz.base.BaseView;
import appinventiv.com.newsbuzz.model.home.headline.NewsHeadline;

/**
 * Created by admin1 on 2/5/18.
 */

public interface HeadlineView extends BaseView{

    void showHeadlines(NewsHeadline headline);

    void showSearchNews(NewsHeadline headline);
}
