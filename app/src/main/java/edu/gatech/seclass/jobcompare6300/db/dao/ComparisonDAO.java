package edu.gatech.seclass.jobcompare6300.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.*;

import edu.gatech.seclass.jobcompare6300.db.entity.Comparison;

@Dao
public interface ComparisonDAO {

    @Query("SELECT * FROM comparison_table ORDER BY comparisonId DESC LIMIT 1")
    public Comparison getCurrentComparison();

    @Insert
    public void addComparison(Comparison comparison);

    @Update
    public void updateComparison(Comparison comparison);

    @Delete
    public void deleteComparison(Comparison comparison);

    @Query("DELETE FROM comparison_table")
    void deleteAll();
}
