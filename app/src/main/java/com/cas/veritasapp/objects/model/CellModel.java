package com.cas.veritasapp.objects.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.evrencoskun.tableview.sort.ISortableModel;

public class CellModel<T> implements ISortableModel {

    private String id;
    private T data;

    public CellModel(String id, T data){
        this.id = id;
        this.data = data;
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @Nullable
    @Override
    public Object getContent() {
        return data;
    }
}
