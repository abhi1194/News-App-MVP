package appinventiv.com.newsbuzz.base;


import appinventiv.com.newsbuzz.model.FailureResponse;

/**
 * Created by appinventiv on 23/1/18.
 */

public interface BaseModelListener {
    void noNetworkError();
    void onErrorOccurred(FailureResponse failureResponse);
}