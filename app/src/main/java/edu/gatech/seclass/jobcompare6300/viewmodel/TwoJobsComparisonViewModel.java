package edu.gatech.seclass.jobcompare6300.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;

import edu.gatech.seclass.jobcompare6300.JobCompare6300Repository;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;

public class TwoJobsComparisonViewModel extends AndroidViewModel {
    private final JobCompare6300Repository mJobCompare6300Repository;

    public TwoJobsComparisonViewModel(Application application) {
        super(application);
        mJobCompare6300Repository = new JobCompare6300Repository(application);
    }

    public void adjustForCostOfLiving(ArrayList<Job> selectedJobs) {

    }
}
