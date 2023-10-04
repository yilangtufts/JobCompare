package edu.gatech.seclass.jobcompare6300.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import java.util.*;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;
import edu.gatech.seclass.jobcompare6300.viewmodel.JobOfferEditorViewModel;
import edu.gatech.seclass.jobcompare6300.viewmodel.JobOffersListActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button viewCurrentJobBtn, jobOffersBtn, comparisonSettingsBtn, compareJobOffersBtn;
    private List<Job> allJobs;
    private JobOffersListActivityViewModel mJobOffersListActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewCurrentJobBtn = (Button) findViewById(R.id.viewCurrentJob);
        jobOffersBtn = (Button) findViewById(R.id.viewJobOffers);
        comparisonSettingsBtn = (Button) findViewById(R.id.comparisonSettings);
        compareJobOffersBtn = (Button) findViewById(R.id.compareJobOffers);

        mJobOffersListActivityViewModel = new ViewModelProvider(this).get(JobOffersListActivityViewModel.class);

        allJobs = mJobOffersListActivityViewModel.getJobsOrderByRankScore();
        if(allJobs.isEmpty() || allJobs.size() < 2) {
            compareJobOffersBtn.setEnabled(false);
        }

        viewCurrentJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent currentJobScreen = new Intent(getBaseContext(), CurrentJobEditorActivity.class);
                startActivity(currentJobScreen);
            }
        });

        jobOffersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentJobScreen = new Intent(getBaseContext(), JobOfferEditorActivity.class);
                startActivity(currentJobScreen);
            }
        });

        comparisonSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentJobScreen = new Intent(getBaseContext(), ComparisonSettingsActivity.class);
                startActivity(currentJobScreen);
            }
        });

        compareJobOffersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentJobScreen = new Intent(getBaseContext(), JobOffersListActivity.class);
                startActivity(currentJobScreen);
            }
        });






    }

}