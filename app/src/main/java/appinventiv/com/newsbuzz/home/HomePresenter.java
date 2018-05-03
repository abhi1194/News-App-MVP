package appinventiv.com.newsbuzz.home;

import appinventiv.com.newsbuzz.base.BasePresenter;
import appinventiv.com.newsbuzz.model.home.NewsSource;


public class HomePresenter extends BasePresenter<HomeView> implements HomeModelListener {

    private HomeModel model;
    public HomePresenter(HomeView view){
        super(view);
    }

    @Override
    protected void setModel() {
        model = new HomeModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;

    }

    @Override
    public void onNewsSourcesFetched(NewsSource newsSource) {
            getView().showNewsSources(newsSource);
    }

    @Override
    public void onCategoryNewsFetched(NewsSource source) {
        getView().showCategoryNews(source);
    }


    void onHomeScreenLoading(){
        model.getNews();
    }

    void onNewsSourceSelected(int position){
        getView().openHeadlineActivity(position);

    }

    public void onCategorySelected(String category) {
        model.getCategoryNews(category);

    }
}
