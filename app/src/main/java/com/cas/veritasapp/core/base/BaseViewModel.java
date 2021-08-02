package com.cas.veritasapp.core.base;

import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.Disposable;

/**
 * Created by funmiayinde on 2019-10-12.
 */
public class BaseViewModel extends ViewModel implements Observable {

    protected Disposable disposable;
    protected PropertyChangeRegistry callbacks = new PropertyChangeRegistry();


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
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

    protected void disposeDisposables() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


}
