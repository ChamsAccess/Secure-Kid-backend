package com.cas.veritasapp.core.base;

import androidx.paging.PagedList;

import java.util.HashMap;

import io.reactivex.Flowable;

/**
 * Created by funmiayinde on 2019-10-12.
 */
public interface BaseRepository<T, P> {

    Flowable<T> create(P data, HashMap map);

    Flowable<T> edit(String id, P data, HashMap hashMap);

    Flowable<T> get(String id, HashMap hashMap);

    Flowable<PagedList<T>> find(HashMap query);

    Flowable<T> delete(String id);

}
