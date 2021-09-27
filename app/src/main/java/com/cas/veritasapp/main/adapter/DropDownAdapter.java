package com.cas.veritasapp.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cas.veritasapp.R;
import com.cas.veritasapp.objects.DropDownObject;

import java.util.ArrayList;

public class DropDownAdapter extends ArrayAdapter<DropDownObject> {

    private Context context;
    private ArrayList<DropDownObject> dropDownObjectList;

    public DropDownAdapter(@NonNull Context context, @NonNull ArrayList<DropDownObject> objects) {
        super(context, 0, objects);
        this.context = context;
        this.dropDownObjectList = objects;
    }



    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.drop_down_list_item, parent, false);

        DropDownObject dropDownObject = dropDownObjectList.get(position);

        TextView textView = listItem.findViewById(R.id.title);
        textView.setText(dropDownObject.getName());
        return listItem;
    }
}
