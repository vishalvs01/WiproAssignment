package com.wiproassignment.aboutcanada.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wiproassignment.R;
import com.wiproassignment.aboutcanada.data.Info;
import com.wiproassignment.databinding.ItemAboutCanadaListBinding;

import java.util.ArrayList;

public class AboutCanadaAdapter extends RecyclerView.Adapter<AboutCanadaAdapter.AboutCanadaViewHolder> {

    private ArrayList<Info> infoList;

    @NonNull
    @Override
    public AboutCanadaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemAboutCanadaListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_about_canada_list, parent, false);

        return new AboutCanadaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutCanadaViewHolder holder, int position) {

        holder.bindData(holder.getAdapterPosition());

    }

    @Override
    public int getItemCount() {
        return infoList == null ? 0 : infoList.size();
    }

    public void addAll(ArrayList<Info> newInfoList) {
        if (infoList == null)
            infoList = new ArrayList<>();

        infoList.addAll(newInfoList);
        notifyDataSetChanged();
    }

    class AboutCanadaViewHolder extends RecyclerView.ViewHolder {

        private ItemAboutCanadaListBinding binding;

        AboutCanadaViewHolder(ItemAboutCanadaListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bindData(int position) {

            Info info = infoList.get(position);

            binding.setInfo(info);

        }
    }

}

