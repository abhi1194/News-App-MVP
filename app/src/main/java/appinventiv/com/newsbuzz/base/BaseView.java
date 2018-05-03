package appinventiv.com.newsbuzz.base;


import appinventiv.com.newsbuzz.model.FailureResponse;

/**
 * Created by appinventiv on 23/1/18.
 */

public interface BaseView {

    void showNoNetworkError();
    void showToastLong(String message);
    void showSpecificError(FailureResponse failureResponse);
    void showProgressDialog();
    void hideProgressDialog();
    void hideKeyboard();
//    void showKeyboard();
}
