package com.cas.veritasapp.core.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cas.veritasapp.R;
import com.cas.veritasapp.util.LogUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by funmiayinde on 2019-08-03.
 * This is a simple adapter class for the recycler view to make things easier
 */
public abstract class BaseStateAdapter extends RecyclerView.Adapter {

    public static final int VIEW_NORMAL = 100;
    public static final int VIEW_EMPTY = 101;
    public static final int VIEW_LOADING = 102;
    public static final int VIEW_ERROR = 103;
    public static final int VIEW_NO_INTERNET = 104;
    /* Internally used values for Different View States */

    public static final int VIEW_TYPE_NORMAL = 200;
    public static final int VIEW_TYPE_EMPTY = 201;
    public static final int VIEW_TYPE_LOADING = 202;
    public static final int VIEW_TYPE_ERROR = 203;
    public static final int VIEW_TYPE_NO_INTERNET = 204;

    private View loadingView = null;
    private View emptyView = null;
    private View errorView = null;
    private View noInternetView = null;

    @CurrentSetView
    private int currentView = VIEW_LOADING;

    protected View viewRoot;
    protected List list;

    public BaseStateAdapter(View viewRoot, List list) {
        this.viewRoot = viewRoot;
        this.list = list;
        notifyDataSetChanged();
    }

    public void setCurrentView(@CurrentSetView int currentView) {
        this.currentView = currentView;
        this.notifyDataSetChanged();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtil.write("currentView " + currentView);
        switch (currentView) {
            case VIEW_NORMAL:
                return bindView(parent, viewType);
            case VIEW_EMPTY:
                if (emptyView == null) {
                    emptyView = LayoutInflater.from(viewRoot.getContext()).inflate(R.layout.view_empty, parent, false);
                    return new RecyclerView.ViewHolder(emptyView) {
                    };
                } else {
                    return new RecyclerView.ViewHolder(emptyView) {
                    };
                }
            case VIEW_ERROR:
                if (errorView == null) {
                    errorView = LayoutInflater.from(viewRoot.getContext()).inflate(R.layout.view_error, parent, false);
                    return new RecyclerView.ViewHolder(errorView) {
                    };
                } else {
                    return new RecyclerView.ViewHolder(errorView) {
                    };
                }
            case VIEW_NO_INTERNET:
                if (noInternetView == null) {
                    noInternetView = LayoutInflater.from(viewRoot.getContext()).inflate(R.layout.view_no_internet, parent, false);
                    return new RecyclerView.ViewHolder(noInternetView) {
                    };
                } else {
                    return new RecyclerView.ViewHolder(noInternetView) {
                    };
                }
            default:
            case VIEW_LOADING:
                if (loadingView == null) {
                    loadingView = LayoutInflater.from(viewRoot.getContext()).inflate(R.layout.view_loading, parent, false);
                    return new RecyclerView.ViewHolder(loadingView) {
                    };
                } else {
                    return new RecyclerView.ViewHolder(loadingView) {
                    };
                }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (currentView) {
            case VIEW_NORMAL:
                bindItem(holder, position);
                break;
            case VIEW_EMPTY:
                onBindEmptyViewHolder(holder, position);
                break;
            case VIEW_ERROR:
                onBindErrorViewHolder(holder, position);
                break;
            case VIEW_NO_INTERNET:
                onBindNoInternetViewHolder(holder, position);
                break;
            default:
            case VIEW_LOADING:
                onBindLoadingViewHolder(holder, position);
                break;
        }
    }

    /**
     * Override this Method to do something when the Empty View is bound
     */
    public void onBindEmptyViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    /**
     * Override this Method to perform actions when the Loading View is bound
     */
    public void onBindLoadingViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    /**
     * Override this Method to perform actions when the Error View is bound
     */
    public void onBindErrorViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    /**
     * Override this Method to perform actions when the Internet View is bound
     */
    public void onBindNoInternetViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        LogUtil.write("View count " + list.size());
        LogUtil.write("onBindViewHolder " + currentView);
        if (list.size() == 0) {
            return 1;
        } else {
            currentView = VIEW_NORMAL;
            return list.size();
        }
    }

    /*
     * Default method of RecyclerView.Adapter
     */
    @Override
    public int getItemViewType(int position) {
        LogUtil.write("View position " + currentView);
        switch (currentView) {
            case VIEW_NORMAL:
                return VIEW_TYPE_NORMAL;
            case VIEW_EMPTY:
                return VIEW_TYPE_EMPTY;
            case VIEW_ERROR:
                return VIEW_TYPE_ERROR;
            case VIEW_NO_INTERNET:
                return VIEW_TYPE_NO_INTERNET;
            default:
            case VIEW_LOADING:
                return VIEW_TYPE_LOADING;
        }
    }

    public abstract RecyclerView.ViewHolder bindView(ViewGroup parent, int viewType);

    public abstract void bindItem(RecyclerView.ViewHolder holder, int position);

    /**
     * Make sure that the Views are only one of the defined values
     */
    @IntDef({VIEW_NORMAL, VIEW_EMPTY, VIEW_LOADING, VIEW_ERROR, VIEW_NO_INTERNET})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CurrentSetView {
    }
}
