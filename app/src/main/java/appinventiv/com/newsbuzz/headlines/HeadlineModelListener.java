package appinventiv.com.newsbuzz.headlines;

import appinventiv.com.newsbuzz.base.BaseModelListener;
import appinventiv.com.newsbuzz.model.home.headline.NewsHeadline;

/**
 * Created by admin1 on 2/5/18.
 */

interface HeadlineModelListener extends BaseModelListener {

    void onSourceHeadlinesFetched(NewsHeadline newsHeadline);
    void onSearchNewsFetched(NewsHeadline headline);
}
