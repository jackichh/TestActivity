package com.example.testactivity.entities;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity (tableName = "dictionaries")

public class Dictionary implements Serializable {

        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "dictionary_name")
        private String dictionaryName;

        @ColumnInfo(name = "words")
        private String words;

        @ColumnInfo(name = "translations")
        private String translations;

        public String getWords() {
                return words;
        }

        public void setWords(String words) {
                this.words = words;
        }

        public String getTranslations() {
                return translations;
        }

        public void setTranslations(String translations) {
                this.translations = translations;
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
}