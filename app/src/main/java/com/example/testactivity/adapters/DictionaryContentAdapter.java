package com.example.testactivity.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testactivity.R;
import com.example.testactivity.activities.HomeActivity;
import com.example.testactivity.entities.Dictionary;
import com.example.testactivity.models.WordTranslationModel;
import com.example.testactivity.ui.dictionarydetail.DictionaryDetailFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

    public class DictionaryContentAdapter extends RecyclerView.Adapter<DictionaryContentAdapter.DictionaryContentViewHolder> {
        private ArrayList<WordTranslationModel> dictContentList = new ArrayList<>();

        private WordTranslationModel dictItem;
        private int tempSize;
        private TextView word, translation;
        private String[] words;
        private String[] translations;
        private Context context;

        boolean isWordChanged = false;
        boolean isTranslationChanged = false;


        public DictionaryContentAdapter(ArrayList<WordTranslationModel> dictContentList, Context context) {
            this.dictContentList=dictContentList;
            this.context=context;
        }


        @NonNull
        @Override
        public DictionaryContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_container_dictionary,parent,false);
            return new DictionaryContentViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull DictionaryContentViewHolder holder, int position) {
            int pos = holder.getAdapterPosition();
            WordTranslationModel tempModel = dictContentList.get(pos);


            holder.Word.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    tempModel.setWord(s.toString());
                }
            });

            holder.Translation.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    tempModel.setTranslation(s.toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return dictContentList.size();
        }

        public void clearText(String word, String translation){
            this.word.setText("");
            this.translation.setText("");
        }

        public void addDictItem(ArrayList<WordTranslationModel> dictContentList, WordTranslationModel dictItem){
            this.dictContentList = dictContentList;
            this.dictItem = dictItem;
            dictContentList.add(dictItem);
            notifyDataSetChanged();
        }

        public void addWordSpace(ArrayList<WordTranslationModel> dictContentList){
            this.dictContentList = dictContentList;
            WordTranslationModel emptyModel = new WordTranslationModel();
            emptyModel.setWord("");
            emptyModel.setTranslation("");
            dictContentList.add(emptyModel);
        }



        static class DictionaryContentViewHolder extends RecyclerView.ViewHolder{
            EditText Word, Translation;
            LinearLayout Item;
            DictionaryContentViewHolder(@NonNull View itemView) {
                super(itemView);
                Word = itemView.findViewById(R.id.inputWord);
                Translation = itemView.findViewById(R.id.inputTranslation);
                Item = itemView.findViewById(R.id.layoutDictionaryItem);
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

        public TextView getWord() {
            return word;
        }

        public void setWord(TextView word) {
            this.word = word;
        }

        public TextView getTranslation() {
            return translation;
        }

        public void setTranslation(TextView translation) {
            this.translation = translation;
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
