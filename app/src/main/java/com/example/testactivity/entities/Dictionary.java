package com.example.testactivity.entities;

import android.util.ArrayMap;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


@Entity (tableName = "dictionaries")

public class Dictionary implements Serializable {

        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "dictionary_name")
        private String dictionaryName;

        @ColumnInfo(name = "word")
        private String word;

        @ColumnInfo(name = "translation")
        private String translation;

        public String getWord() {
                return word;
        }

        public void setWord(String word) {
                this.word = word;
        }

        public String getTranslation() {
                return translation;
        }

        public void setTranslation(String translation) {
                this.translation = translation;
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