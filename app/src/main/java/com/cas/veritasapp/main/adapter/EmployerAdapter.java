package com.cas.veritasapp.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.data.entities.Employer;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.objects.DropDownObject;
import com.cas.veritasapp.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployerAdapter extends ArrayAdapter<Employer> {
    private Context context;
    private ArrayList<Employer> employerArrayList;
    private ArrayList<Employer> employersList;
    private ArrayList<Employer> suggestions;
    private OnItemSelectedListener listener;

    public EmployerAdapter(@NonNull Context context, @NonNull ArrayList<Employer> objects, OnItemSelectedListener listener) {
        super(context, 0, objects);
        this.context = context;
        this.listener = listener;
        this.suggestions = new ArrayList<>();
        this.employerArrayList = objects;
        this.employersList = (ArrayList<Employer>) employerArrayList.clone();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        public String convertResultToString(Object value) {
            return ((Employer) (value)).getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Employer employer : employersList) {
                    if (employer.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(employer);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            }
            return new FilterResults();
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Employer> filterList = (ArrayList<Employer>) results.values;
            if (results != null && results.count > 0){
                clear();
                for (Employer employer: filterList){
                    add(employer);
                }
                notifyDataSetChanged();
            }
        }
    };

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.drop_down_list_item, parent, false);

        Employer employer = employerArrayList.get(position);
        TextView textView = listItem.findViewById(R.id.title);
        textView.setText(employer.getName());
        listItem.setOnClickListener((v) -> {
            listener.ontItemSelected(employer, AppConstant.SHOW_EMPLOYER_DETAILS);
            clear();
        });
        return listItem;
    }
}
