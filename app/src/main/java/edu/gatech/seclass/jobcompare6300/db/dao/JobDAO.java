package edu.gatech.seclass.jobcompare6300.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.*;

import edu.gatech.seclass.jobcompare6300.db.entity.Job;

@Dao
public interface JobDAO {

    @Query("SELECT * FROM job_table ORDER BY weightScore DESC")
    public List<Job> getJobsOrderByRankScore();

    @Insert
    public void addJob(Job job);

    @Update
    public void updateJob(Job job);

    @Delete
    public void deleteJob(Job job);

    @Query("SElECT * FROM job_table WHERE isCurrentJob = 1 ")
    public Job showCurrentJob();

    @Query("DELETE FROM job_table")
    void deleteAll();
}
