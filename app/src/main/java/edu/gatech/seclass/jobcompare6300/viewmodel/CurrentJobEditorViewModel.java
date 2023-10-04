package edu.gatech.seclass.jobcompare6300.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import edu.gatech.seclass.jobcompare6300.JobCompare6300Repository;
import edu.gatech.seclass.jobcompare6300.db.entity.Comparison;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;

public class CurrentJobEditorViewModel extends AndroidViewModel {

    private final JobCompare6300Repository mJobCompare6300Repository;

    public CurrentJobEditorViewModel(Application application) {
        super(application);
        mJobCompare6300Repository = new JobCompare6300Repository(application);
    }

    public Job getCurrentJob() {
        return mJobCompare6300Repository.getmJobDao().showCurrentJob();
    }

    public void addNewCurrentJob(Job job) {
        Comparison curComparison = mJobCompare6300Repository.getmComparisonDAO().getCurrentComparison();
        job.setWeightScore(curComparison);
        mJobCompare6300Repository.getmJobDao().addJob(job);
    }

    public void deleteCurrentJob(Job job) {
        mJobCompare6300Repository.getmJobDao().deleteJob(job);
    }
}
