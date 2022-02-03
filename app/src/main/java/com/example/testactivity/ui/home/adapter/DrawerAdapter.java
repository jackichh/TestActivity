package com.example.testactivity.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.HomeActivity;
import com.example.testactivity.R;

import java.util.ArrayList;

public class DrawerAdapter  extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {

    private ArrayList<String> menuList;
    private Context mContext;



    public DrawerAdapter(ArrayList<String> menuList, Context context) {
        this.menuList = menuList;
        this.mContext  = context;
    }

    @NonNull
    @Override
    public DrawerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dictionary,parent,false);
        return new DrawerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerViewHolder holder, final int position) {
        holder.menuName.setText(menuList.get(position));
        holder.itemView.setOnClickListener(view -> {
            if (mContext instanceof HomeActivity){
                ((HomeActivity)mContext).onItemSelected(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public void addDrawerMenuList(ArrayList<String> menuList){
        this.menuList = menuList;
        notifyDataSetChanged();
    }

    static class DrawerViewHolder extends RecyclerView.ViewHolder{
        TextView menuName;

        DrawerViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.dictionary_item_title);
        }

    }

}
