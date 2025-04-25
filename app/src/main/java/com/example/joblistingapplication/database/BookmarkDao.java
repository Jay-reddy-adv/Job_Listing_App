package com.example.joblistingapplication.database;import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface BookmarkDao {
    @Insert
    void insert(Bookmark bookmark);

    @Query("SELECT * FROM Bookmark")
    List<Bookmark> getAll();

    @Query("DELETE FROM Bookmark")
    void clearAll();
}
