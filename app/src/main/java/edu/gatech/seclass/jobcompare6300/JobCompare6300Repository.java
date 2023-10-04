package edu.gatech.seclass.jobcompare6300;

import android.app.Application;
import android.provider.LiveFolders;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.db.JobCompare6300RoomDatabase;
import edu.gatech.seclass.jobcompare6300.db.dao.ComparisonDAO;
import edu.gatech.seclass.jobcompare6300.db.dao.JobDAO;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;

public class JobCompare6300Repository {

    private JobDAO mJobDao;
    private ComparisonDAO mComparisonDAO;

    public JobCompare6300Repository(Application application) {
        JobCompare6300RoomDatabase db = JobCompare6300RoomDatabase.getDatabase(application);
        mJobDao = db.jobDao();
        mComparisonDAO = db.comparisonDAO();
    }

    public JobDAO getmJobDao() {
        return mJobDao;
    }

    public void setmJobDao(JobDAO mJobDao) {
        this.mJobDao = mJobDao;
    }

    public ComparisonDAO getmComparisonDAO() {
        return mComparisonDAO;
    }

    public void setmComparisonDAO(ComparisonDAO mComparisonDAO) {
        this.mComparisonDAO = mComparisonDAO;
    }

}
