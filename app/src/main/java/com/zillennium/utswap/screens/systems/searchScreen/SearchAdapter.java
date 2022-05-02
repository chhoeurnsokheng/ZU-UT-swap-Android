package com.zillennium.utswap.screens.systems.searchScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private ArrayList<Search> listdata;
    private SearchAdapter.OnclickItemSearch onclickItemSearch;

    public SearchAdapter(ArrayList<Search> arrayList, OnclickItemSearch onclickItemSearch) {
        this.listdata = arrayList;
        this.onclickItemSearch = onclickItemSearch;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_system_search, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Search search = this.listdata.get(i);
        viewHolder.txt_history.setText(search.getTitle());
        viewHolder.txt_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickItemSearch.onClickMe(search.getTitle());
            }
        });
    }

    public int getItemCount() {
        return this.listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_history;

        public ViewHolder(View view) {
            super(view);
            this.txt_history = (TextView) view.findViewById(R.id.txt_history);
        }
    }

    public interface OnclickItemSearch{
        void onClickMe(String textSearch);
    }
}
