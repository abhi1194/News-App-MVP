package appinventiv.com.newsbuzz.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import appinventiv.com.newsbuzz.R;
import appinventiv.com.newsbuzz.adapter.NewsAdapter;
import appinventiv.com.newsbuzz.base.BaseActivity;
import appinventiv.com.newsbuzz.headlines.HeadlineActivity;
import appinventiv.com.newsbuzz.interfaces.OnRowItemClickListener;
import appinventiv.com.newsbuzz.model.home.NewsSource;
import appinventiv.com.newsbuzz.model.home.Source;
import appinventiv.com.newsbuzz.utils.AppConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements HomeView, OnRowItemClickListener {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.rv_show_news)
    RecyclerView rvShowNews;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private HomePresenter homePresenter;
    private NewsAdapter mAdapter;
    private ArrayList<Source> mNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter(this);
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.news));
        setAdapter();
        homePresenter.onHomeScreenLoading();
        showProgressDialog();
    }

    private void setAdapter(){
        mNewsList = new ArrayList<>();
        rvShowNews.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NewsAdapter(mNewsList, this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvShowNews.getContext(), DividerItemDecoration.VERTICAL);
        rvShowNews.addItemDecoration(dividerItemDecoration);
        rvShowNews.setAdapter(mAdapter);
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_home;
    }


    @Override
    public void showNewsSources(NewsSource newsSource) {
        hideProgressDialog();
        if(mNewsList.size()>0){
            mNewsList.clear();
        }
        mNewsList.addAll(newsSource.getSources());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openHeadlineActivity(int pos) {
        Intent intent = new Intent(this, HeadlineActivity.class);
        intent.putExtra(AppConstant.SOURCE_ID, mNewsList.get(pos).getId());
        intent.putExtra(AppConstant.SOURCE_NAME, mNewsList.get(pos).getName());
        startActivity(intent);
    }

    @Override
    public void showCategoryNews(NewsSource newsSource) {
        hideProgressDialog();
        mNewsList.clear();
        mNewsList.addAll(newsSource.getSources());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRowItemClicked(int position) {
        homePresenter.onNewsSourceSelected(position);
    }

    /**
     * This method is used to show the categories of all the news
     */
    private void showCategoryDialog(){
        final AlertDialog alert = new AlertDialog.Builder(this).create();
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_categories,null);
        RadioGroup rgCategory = view.findViewById(R.id.rg_categories);
        alert.setTitle(getString(R.string.choose_category));
        alert.setView(view);
        alert.show();
        rgCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_all:
                        homePresenter.onHomeScreenLoading();
                        break;
                    case R.id.rb_business:
                        homePresenter.onCategorySelected("business");
                        break;
                    case R.id.rb_entertainment:
                        homePresenter.onCategorySelected("entertainment");
                        break;
                    case R.id.rb_general:
                        homePresenter.onCategorySelected("general");
                        break;
                    case R.id.rb_health:
                        homePresenter.onCategorySelected("health");
                        break;
                    case R.id.rb_science:
                        homePresenter.onCategorySelected("science");
                        break;
                    case R.id.rb_sports:
                        homePresenter.onCategorySelected("sports");
                        break;
                    case R.id.rb_technology:
                        homePresenter.onCategorySelected("technology");
                }
                alert.dismiss();
                showProgressDialog();

            }
        });

    }

    @OnClick(R.id.iv_category)
    public void onViewClicked() {
        showCategoryDialog();
    }
}
