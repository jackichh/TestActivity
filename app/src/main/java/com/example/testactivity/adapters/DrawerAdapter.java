package com.example.testactivity.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.activities.HomeActivity;
import com.example.testactivity.R;
import com.example.testactivity.entities.Dictionary;

import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DictionaryViewHolder> {

    private List <Dictionary> dictList;
    private Dictionary dictItem;
    private Context mContext;


    public Dictionary getDictItem() {
        return dictItem;
    }

    public void setDictItem(Dictionary dictItem) {
        this.dictItem = dictItem;
    }

    public DrawerAdapter(List <Dictionary> dictList, Context context) {
        this.dictList = dictList;
        this.mContext  = context;
    }


    @NonNull
    @Override
    public DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dictionary,parent,false);
        return new DictionaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryViewHolder holder, int position) {
        holder.Name.setText(dictList.get(position).getDictionaryName());
        holder.Item.setOnClickListener(view -> {
            if (mContext instanceof HomeActivity){
                ((HomeActivity)mContext).onItemClicked(position);
            }
        });
        holder.Item.setOnLongClickListener(view -> {
            Dictionary dictionary= new Dictionary();
            int ID = dictList.get(holder.getAdapterPosition()).getId();
            dictionary.setId(ID);
            HomeActivity.dictionariesDatabase.dictionaryDao().deleteDeleteDictionary(dictionary);
            ((HomeActivity)mContext).onItemLongClicked(position);
            notifyItemChanged(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return dictList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void deleteDrawerItem(int pos){
        dictList.remove(pos);
        notifyDataSetChanged();

    }

    public String getItemText(int position) {
        return dictList.get(position).getDictionaryName();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addDrawerItem(List<Dictionary> dictList, Dictionary dictItem){
        this.dictList = dictList;
        this.dictItem = dictItem;
        dictList.add(dictItem);
        notifyDataSetChanged();

    }

    static class DictionaryViewHolder extends RecyclerView.ViewHolder{
        TextView Name;
        CardView Item;
        DictionaryViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.dictionary_item_title);
            Item = itemView.findViewById(R.id.dictionary_card);
        }

    }

}

