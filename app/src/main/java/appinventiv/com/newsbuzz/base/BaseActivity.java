package appinventiv.com.newsbuzz.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import appinventiv.com.newsbuzz.R;
import appinventiv.com.newsbuzz.model.FailureResponse;
import butterknife.ButterKnife;

/**
 * Created by appinventiv on 23/1/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private RelativeLayout baseContainer;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        baseContainer = findViewById(R.id.base_container);
        setLayout();
        ButterKnife.bind(this);
        mProgress = new ProgressDialog(this);
    }

    private void setLayout() {
        if (getResourceId() != -1) {
            removeLayout();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                View view = inflater.inflate(getResourceId(), null);
                baseContainer.addView(view, layoutParams);
            }
        }
    }

    private void removeLayout() {
        if (baseContainer.getChildCount() >= 1)
            baseContainer.removeAllViews();
    }

    protected abstract int getResourceId();


    /**
     * A common place to handle no network error
     * Can show a full screen View, Snackbar with retry action
     * or a simple Toast
     */

    @Override
    public void showNoNetworkError() {
        showToastLong(getString(R.string.no_network_error));
    }

    @Override
    public void showToastLong(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method helps both ways
     * Using this generic handling as well as Specific handling can be done
     *
     * @param failureResponse contains errorCode
     *                        which can decide what kind of handling can be done
     */

    @Override
    public void showSpecificError(FailureResponse failureResponse) {
        String message = (failureResponse != null) ? failureResponse.getMsg() : getString(R.string.something_went_wrong);
        showToastLong(message);
    }

    @Override
    public void showProgressDialog() {
        mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgress.setTitle(getString(R.string.news_loading));
        mProgress.setCancelable(false);
        mProgress.show();
    }

    @Override
    public void hideProgressDialog() {
        mProgress.hide();

    }

    @Override
    public void hideKeyboard() {
        try {
            InputMethodManager inputManager = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow((this).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Log.e("BaseActivity", "Sigh, cant even hide keyboard " + e.getMessage());
        }

    }
    /*
    @Override
    public void showKwyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText2, InputMethodManager.SHOW_IMPLICIT);
    }*/
}
