package edu.gatech.seclass.jobcompare6300.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.JobCompare6300Repository;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;

public class JobOffersListActivityViewModel extends AndroidViewModel  {
    private final JobCompare6300Repository mJobCompare6300Repository;

    public JobOffersListActivityViewModel(Application application) {
        super(application);
        mJobCompare6300Repository = new JobCompare6300Repository(application);
    }

    public List<Job> getJobsOrderByRankScore() {
        List<Job> jobs = mJobCompare6300Repository.getmJobDao().getJobsOrderByRankScore();
        return jobs;
    }
}
