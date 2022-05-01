package com.example.testactivity.typeconverters;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class WordsConverter {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public String fromWords(List<String> words) {
        return String.join(",", words);
    }

    @TypeConverter
    public List<String> toWords(String data) {
        return Arrays.asList(data.split(","));
    }
}

