package com.example.testactivity.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Dictionary{

        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "dictionary_name")
        private String dictionaryName;

        @ColumnInfo(name = "word")
        private String word;

        @ColumnInfo(name = "translation")
        private String translation;

        @ColumnInfo(name = "is_checked")
        private boolean isChecked;

        /////////////////////////////////////////

        public String getWord() {
                return word;
        }

        public void setWord(String word) {
                this.word = word;
        }

        public String getTranslation() {
                return translation;
        }

        public void setTranslation(String translations) {
                this.translation = translations;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getDictionaryName() {
                return dictionaryName;
        }

        public void setDictionaryName(String dictionaryName) {
                this.dictionaryName = dictionaryName;
        }

        public boolean isChecked() {
                return isChecked;
        }

        public void setChecked(boolean checked) {
                isChecked = checked;
        }
}