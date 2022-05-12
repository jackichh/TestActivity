package com.example.testactivity.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.R;
import com.example.testactivity.Models.WordTranslationModel;

import java.util.ArrayList;

public class DictionaryContentAdapter extends RecyclerView.Adapter<DictionaryContentAdapter.DictionaryContentViewHolder> implements Filterable {
    private ArrayList<WordTranslationModel> dictContentList, dictContentListFull;

    private WordTranslationModel dictItem;
    private final Context context;



    public DictionaryContentAdapter(ArrayList<WordTranslationModel> dictContentList, Context context) {
        this.dictContentList = dictContentList;
        this.context = context;
        dictContentListFull = new ArrayList<>(dictContentList);
    }


    @NonNull
    @Override
    public DictionaryContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_container_dictionary, parent, false);
        return new DictionaryContentViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DictionaryContentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.wtf("Position", position + " ");
        holder.translation.setText(dictContentList.get(position).getTranslation());
        holder.word.setText(dictContentList.get(position).getWord());
        holder.checkBox.setChecked(dictContentList.get(position).isChecked());


        holder.word.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dictContentList.get(position).setWord(s.toString());
            }
        });

        holder.translation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dictContentList.get(position).setTranslation(s.toString());

            }
        });

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) ->
                dictContentList.get(position).setChecked(isChecked));

    }

    @Override
    public int getItemCount() {
        return dictContentList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void deleteItem(int pos){
        dictContentList.remove(pos);
        notifyDataSetChanged();
    }

    static class DictionaryContentViewHolder extends RecyclerView.ViewHolder {
        EditText word, translation;
        LinearLayout item;
        CheckBox checkBox;

        DictionaryContentViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.inputWord);
            translation = itemView.findViewById(R.id.inputTranslation);
            item = itemView.findViewById(R.id.layoutDictionaryItem);
            checkBox = itemView.findViewById(R.id.choose_to_practice);
        }

    }

    public ArrayList<WordTranslationModel> getDictContentList() {
        return dictContentList;
    }

    public void setDictContentList(ArrayList<WordTranslationModel> dictList) {
        this.dictContentList = dictList;
    }

    @Override
    public Filter getFilter() {
        return dictionariesFilter;
    }
    private Filter dictionariesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<WordTranslationModel> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(dictContentListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (WordTranslationModel item : dictContentListFull) {
                    if(item.getWord().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dictContentList.clear();
            dictContentList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

}
