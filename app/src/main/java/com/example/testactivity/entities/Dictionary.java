package com.example.testactivity.entities;

import java.io.Serializable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


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
//        @ColumnInfo(name = "")
//        private String ;
}
