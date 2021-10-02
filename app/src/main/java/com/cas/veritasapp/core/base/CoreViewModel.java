package com.cas.veritasapp.core.base;

import android.annotation.SuppressLint;

import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;


import com.cas.veritasapp.BR;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.network.Resource;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.util.AppUtil;
import com.cas.veritasapp.util.LogUtil;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by funmiayinde on 2019-10-12.
 */
public class CoreViewModel<R extends BaseRepository, T, P, V> extends BaseViewModel {

    public MutableLiveData<V> validation = new MutableLiveData<>();

    protected MutableLiveData<Resource<T>> currentListData = new MutableLiveData<>();
    protected MutableLiveData<Resource<T>> currentData = new MutableLiveData<>();
    protected HashMap<String, Object> query = new HashMap<>();

    protected V currentValidation;
    protected R repository;
    protected T current;
    private String error;


    public CoreViewModel() {
    }

    @Bindable
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
//        notifyPropertyChanged(com.chamsaccess.grms_app.BR.error);
    }

    @Bindable
    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
        notifyPropertyChanged(BR.current);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    /**
     * Notifies observers that all properties of this instance have changed
     */
    public void notifyChange() {
        callbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }

    @SuppressLint("CheckResult")
    public void get(String id) {
        performAction(repository.get(id, query), AppConstant.GET_ACTION);
    }

    @SuppressLint("CheckResult")
    public void create(P payload) {
        performAction(repository.create(payload, query), AppConstant.CREATE_ACTION);
    }

    @SuppressLint("CheckResult")
    public void edit(String currentId, P payload) {
        performAction(repository.edit(currentId, payload, query), AppConstant.EDIT_ACTION);
    }

    @SuppressLint("CheckResult")
    public void delete(String currentId) {
        performAction(repository.delete(currentId), AppConstant.DELETE_ACTION);
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Resource<PagedList<T>>> find(HashMap<String, Object> query) {
        MutableLiveData<Resource<PagedList<T>>> listData = new MutableLiveData<>();
        disposeDisposables();
        LogUtil.info("loading list....");
        listData.setValue(Resource.loading(null));
        disposable = repository.find(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    PagedList pagedList = (PagedList) data;
                    listData.setValue(Resource.success(pagedList));
                }, error -> {
                    ApiError apiError = (ApiError) error;
                    listData.setValue(Resource.error(apiError, null));
                });
        return listData;
    }

    public void performAction(Flowable<T> flowable, final String key) {
        disposeDisposables();
        LogUtil.info("loading.....data");
        currentData.setValue(Resource.loading(null, key));
        disposable = flowable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(current -> {
                    currentData.setValue(Resource.success(current, key));
                }, error -> {
                    ApiError apiError = AppUtil.getError(error);
                    currentData.setValue(Resource.error(apiError, key, null));
                });
    }

    public HashMap<String, Object> getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, Object> query) {
        this.query = query;
    }

    public MutableLiveData<V> getValidation() {
        return validation;
    }

    public void setCurrentValidation(V validation) {
        this.currentValidation = validation;
    }

    public V getCurrentValidation() {
        return currentValidation;
    }

    public MutableLiveData<Resource<T>> getCurrentListData() {
        return currentListData;
    }

    public void setCurrentListData(MutableLiveData<Resource<T>> currentListData) {
        this.currentListData = currentListData;
    }
}
