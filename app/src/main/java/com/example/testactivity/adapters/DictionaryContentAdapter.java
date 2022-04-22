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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.R;
import com.example.testactivity.models.WordTranslationModel;

import java.util.ArrayList;

public class DictionaryContentAdapter extends RecyclerView.Adapter<DictionaryContentAdapter.DictionaryContentViewHolder> {
    private ArrayList<WordTranslationModel> dictContentList;

    private WordTranslationModel dictItem;
    private int tempSize;

    private String[] words;
    private String[] translations;
    private Context context;

    boolean isWordChanged = false;
    boolean isTranslationChanged = false;


    public DictionaryContentAdapter(ArrayList<WordTranslationModel> dictContentList, Context context) {
        this.dictContentList = dictContentList;
        this.context = context;
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

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                           dictContentList.get(position).setChecked(isChecked);
                                                       }
                                                   }

        );

    }

    @Override
    public int getItemCount() {
        return dictContentList.size();
    }


    public void addDictItem(ArrayList<WordTranslationModel> dictContentList, WordTranslationModel dictItem) {
        this.dictContentList = dictContentList;
        this.dictItem = dictItem;
        dictContentList.add(dictItem);
        notifyDataSetChanged();
    }

    public void addWordSpace(ArrayList<WordTranslationModel> dictContentList) {
        this.dictContentList = dictContentList;
        WordTranslationModel emptyModel = new WordTranslationModel();
        emptyModel.setWord("");
        emptyModel.setTranslation("");
        dictContentList.add(emptyModel);
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

    public int getTempSize() {
        return tempSize;
    }

    public void setTempSize(int tempSize) {
        this.tempSize = tempSize;
    }

    public ArrayList<WordTranslationModel> getDictContentList() {
        return dictContentList;
    }

    public void setDictContentList(ArrayList<WordTranslationModel> dictList) {
        this.dictContentList = dictList;
    }

    public WordTranslationModel getDictItem() {
        return dictItem;
    }

    public void setDictItem(WordTranslationModel dictItem) {
        this.dictItem = dictItem;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }

    public String[] getTranslations() {
        return translations;
    }

    public void setTranslations(String[] translations) {
        this.translations = translations;
    }
}
