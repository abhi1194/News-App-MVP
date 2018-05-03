package appinventiv.com.newsbuzz.base;


import java.lang.ref.SoftReference;

import appinventiv.com.newsbuzz.data.DataManager;
import appinventiv.com.newsbuzz.network.CommonResponseHandler;

/**
 * Created by appinventiv on 23/1/18.
 */

public abstract class BaseModel<T extends BaseModelListener> implements CommonResponseHandler {

    private SoftReference<T> listener;

    public BaseModel(T listener) {
        this.listener = new SoftReference<>(listener);
    }

    public void attachListener(T listener) {
        this.listener = new SoftReference<>(listener);
    }

    public void detachListener() {
        this.listener = null;
    }

    public T getListener() {
        return (listener != null) ? listener.get() : null;
    }


    private void noNetworkAvailableError() {
        getListener().noNetworkError();
    }

    @Override
    public void onNetworkError() {
        noNetworkAvailableError();
    }

    public DataManager getDataManager() {
        return DataManager.getInstance();
    }
}
