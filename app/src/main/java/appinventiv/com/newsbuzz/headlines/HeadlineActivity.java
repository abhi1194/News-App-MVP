package appinventiv.com.newsbuzz.headlines;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import appinventiv.com.newsbuzz.R;
import appinventiv.com.newsbuzz.adapter.HeadlineAdapter;
import appinventiv.com.newsbuzz.base.BaseActivity;
import appinventiv.com.newsbuzz.model.home.headline.Headlines;
import appinventiv.com.newsbuzz.model.home.headline.NewsHeadline;
import appinventiv.com.newsbuzz.utils.AppConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeadlineActivity extends BaseActivity implements HeadlineView {

    @BindView(R.id.rv_headlines)
    RecyclerView rvHeadlines;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_category)
    ImageView ivCategory;
    private String mSourceId;
    private HeadlinePresenter presenter;
    private HeadlineAdapter mHeadlineAdapter;
    ArrayList<Headlines> mHeadlinesList,mSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_headline);
        ButterKnife.bind(this);
        presenter = new HeadlinePresenter(this);
        ivCategory.setVisibility(View.GONE);
        ivSearch.setVisibility(View.VISIBLE);

        getValuesFromIntent();

        setAdapter();
        //Hit Headline Api
        presenter.getTopHeadlines(mSourceId);
        showProgressDialog();

        //Search action
        actionOnSearch();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(etSearch.getText().toString())){
                    if(mHeadlinesList.size()>0)
                        mHeadlinesList.clear();
                    mHeadlinesList.addAll(mSearchList);
                    mHeadlineAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void actionOnSearch() {
        etSearch.setOnEditorActionListener(new EditText.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){

                    if(!TextUtils.isEmpty(etSearch.getText().toString())) {
                        hideKeyboard();
                        showProgressDialog();
                        presenter.getSearchedList(etSearch.getText().toString().trim());
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void setAdapter(){
        mSearchList = new ArrayList<>();
        mHeadlinesList = new ArrayList<>();
        rvHeadlines.setLayoutManager(new LinearLayoutManager(this));
        mHeadlineAdapter = new HeadlineAdapter(mHeadlinesList);
        rvHeadlines.setAdapter(mHeadlineAdapter);
    }
    /**
     * This method is used to set the values coming through intent
     * from NewsAdapter
     */
    private void getValuesFromIntent() {
        Intent data = getIntent();
        if (data != null) {
            mSourceId = data.getStringExtra(AppConstant.SOURCE_ID);
            tvTitle.setText(data.getStringExtra(AppConstant.SOURCE_NAME));
        }
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_headline;
    }

    @Override
    public void showHeadlines(NewsHeadline headline) {
        hideProgressDialog();
        if(mHeadlinesList.size()>0)
            mHeadlinesList.clear();
        mHeadlinesList.addAll(headline.getArticles());
        mHeadlineAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSearchNews(NewsHeadline headline) {
        hideProgressDialog();
        mSearchList.addAll(mHeadlinesList);
        mHeadlinesList.clear();
        mHeadlinesList.addAll(headline.getArticles());
        mHeadlineAdapter.notifyDataSetChanged();

    }

    @OnClick(R.id.iv_search)
    public void onViewClicked() {
        etSearch.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.GONE);
    }
}
