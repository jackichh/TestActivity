package com.example.testactivity.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.testactivity.entities.Dictionary;

import java.util.HashMap;
import java.util.List;


@Dao
public interface DictionaryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDictionary(Dictionary dictionary);

    @Query("SELECT * FROM dictionaries")
    List<Dictionary> getAllDictionaries();

    @Query("SELECT * FROM dictionaries WHERE dictionary_name LIKE :search")
    List<Dictionary> getAllWithDictionaryNameLike(String search);

    @Delete
    void deleteDeleteDictionary(Dictionary dictionary);
}