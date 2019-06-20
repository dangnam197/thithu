package com.example.thithu.database.dao;



import com.example.thithu.database.entity.Mark;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface MarkDao {
    @Insert
    void insert(Mark mark);
    @Insert
    void insertList(Mark... marks);
    @Update
    void update(Mark mark);
    @Delete
    void delete(Mark mark);
    @Query("SELECT * FROM Mark WHERE id = :id")
    Mark getMark(int id);
    @Query("SELECT * FROM Mark WHERE type = :type AND sid = :sid")
    Mark getMarkSection(int type, int sid);
}
