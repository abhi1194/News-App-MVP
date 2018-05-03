package appinventiv.com.newsbuzz.headlines;

import appinventiv.com.newsbuzz.base.BasePresenter;
import appinventiv.com.newsbuzz.model.home.headline.NewsHeadline;

/**
 * Created by admin1 on 2/5/18.
 */

public class HeadlinePresenter extends BasePresenter<HeadlineView> implements HeadlineModelListener {

    private HeadlineModel model ;

    public HeadlinePresenter(HeadlineView view){
        super(view);
    }
    @Override
    protected void setModel() {
        model = new HeadlineModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model=null;

    }

    @Override
    public void onSourceHeadlinesFetched(NewsHeadline newsHeadline) {
        getView().showHeadlines(newsHeadline);
    }

    @Override
    public void onSearchNewsFetched(NewsHeadline headline) {
        getView().showSearchNews(headline);
    }

    void getTopHeadlines(String sourceName){
        model.getHeadlines(sourceName);
    }

    public void getSearchedList(String s) {
        model.getSearchedNews(s);
    }
}
