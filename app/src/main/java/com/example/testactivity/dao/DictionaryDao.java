package com.example.testactivity.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.testactivity.entities.Dictionary;

import java.util.List;


@Dao
public interface DictionaryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDictionary(Dictionary dictionary);

    @Query("SELECT * FROM dictionary")
    List<Dictionary> getAllDictionaries();

    @Query("SELECT dictionary_name FROM dictionary")
    List<String> getNames();

    @Query("SELECT * FROM dictionary WHERE dictionary_name LIKE :search")
    List<Dictionary> getAllDictionaryWithNameLike(String search);

    @Query("DELETE FROM dictionary WHERE dictionary_name LIKE :search")
    void deleteDictionariesWithNameLike(String search);

    @Delete
    void deleteDeleteDictionary(Dictionary dictionary);
}