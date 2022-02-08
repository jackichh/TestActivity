package com.example.testactivity.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.HomeActivity;
import com.example.testactivity.R;

import java.util.ArrayList;

public class DrawerAdapter  extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {

    private ArrayList<String> menuList;
    private String menuItem;
    private Context mContext;


    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public DrawerAdapter(ArrayList<String> menuList, Context context) {
        this.menuList = menuList;
        this.mContext  = context;
    }

    public DrawerAdapter(Context context){

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
        holder.item.setOnClickListener(view -> {
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

    public void deleteDrawerMenuItem(ArrayList<String> menuList, int pos){
        this.menuList = menuList;
        menuList.remove(pos);
    }

    public void addDrawerMenuItem(ArrayList<String> menuList, String menuItem){
        this.menuList = menuList;
        this.menuItem = menuItem;
        menuList.add(menuItem);
        notifyDataSetChanged();

    }

    static class DrawerViewHolder extends RecyclerView.ViewHolder{
        TextView menuName;
        CardView item;
        DrawerViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.dictionary_item_title);
            item = itemView.findViewById(R.id.dictionary_card);
        }

    }

}
