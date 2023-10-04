package edu.gatech.seclass.jobcompare6300.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.JobCompare6300Repository;
import edu.gatech.seclass.jobcompare6300.db.entity.Comparison;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;

public class ComparisonsSettingsViewModel extends AndroidViewModel {
    private final JobCompare6300Repository mJobCompare6300Repository;

    public ComparisonsSettingsViewModel(Application application) {
        super(application);
        mJobCompare6300Repository = new JobCompare6300Repository(application);
    }

    public Comparison getCurrentComparison() {
        return mJobCompare6300Repository.getmComparisonDAO().getCurrentComparison();
    }

    public void saveComparisonsSettings(Comparison comparison) {
        Comparison currentComparison = getCurrentComparison();
        currentComparison.setBonusWeight(comparison.getBonusWeight());
        currentComparison.setGymAllowanceWeight(comparison.getGymAllowanceWeight());
        currentComparison.setLeaveWeight(comparison.getLeaveWeight());
        currentComparison.setSalaryWeight(comparison.getSalaryWeight());
        currentComparison.setTeleworkWeight(comparison.getTeleworkWeight());
        mJobCompare6300Repository.getmComparisonDAO().updateComparison(currentComparison);

        List<Job> allJobs = mJobCompare6300Repository.getmJobDao().getJobsOrderByRankScore();
        for( int i= 0; i< allJobs.size(); i++ ) {
            // Update every job's weight Score
            allJobs.get(i).setWeightScore(currentComparison);
            mJobCompare6300Repository.getmJobDao().updateJob(allJobs.get(i));
        }
    }

}
