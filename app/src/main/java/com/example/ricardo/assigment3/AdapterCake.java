package com.example.ricardo.assigment3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterCake extends RecyclerView.Adapter<AdapterCake.ViewHolderItem> implements View.OnClickListener {

    List<Cake> data ;
    private View.OnClickListener listener;
    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cake,null,false);
        view.setOnClickListener(this);
        return new ViewHolderItem(view);
    }

    public AdapterCake(List<Cake> data) {
        this.data = data;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItem viewHolderItem, int i) {
        Cake s = data.get(i);
        viewHolderItem.mTitle.setText(s.getTitle());
        viewHolderItem.mDesc.setText(s.getDesc());
        Picasso.get().load(s.getImage()).fit().into((ImageView) viewHolderItem.mImg);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {
        TextView mTitle,mDesc;
        ImageView mImg;

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
            mDesc = itemView.findViewById(R.id.desc);
            mImg = itemView.findViewById(R.id.img);
        }


    }
}
