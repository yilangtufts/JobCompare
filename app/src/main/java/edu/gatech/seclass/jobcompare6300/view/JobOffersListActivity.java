package edu.gatech.seclass.jobcompare6300.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;
import edu.gatech.seclass.jobcompare6300.viewmodel.JobOfferEditorViewModel;
import edu.gatech.seclass.jobcompare6300.viewmodel.JobOffersListActivityViewModel;

public class JobOffersListActivity extends AppCompatActivity {


    /*
    Pull array of all Jobs
    Order Jobs
    Display jobs
     */


    ListView rank_jobs_list;
    private Button cancelButtonJobOfferlist, compareButtonJobOffList;
    private JobOffersListActivityViewModel mJobOffersListActivityViewModel;
    private List<Job> allJobs;
    private List<Job> selectedJobs;
    private void selectTwoJobsToCompare(View view, int i) {
        CheckedTextView castView= (CheckedTextView) view;
        if (castView.isChecked()) {
            selectedJobs.add(allJobs.get(i));
        } else {
            selectedJobs.remove(allJobs.get(i));
        }

        // only selected 2 jobs will trigger comparison
        if (selectedJobs.size() == 2) {
            compareButtonJobOffList.setEnabled(true);
        } else {
            compareButtonJobOffList.setEnabled(false);
        }
    };

    private void loadJobsToListView() {
        List<String> jobDisplay = new ArrayList<String>();
        for( int i = 0; i< allJobs.size(); i++) {
            if (allJobs.get(i).isCurrentJob()) {
                jobDisplay.add( "(Current job) Title: " + allJobs.get(i).getTitle() + "\n" + "Company: " + allJobs.get(i).getCompany());
            } else {
                jobDisplay.add( "Title: " + allJobs.get(i).getTitle() + "\n" + "Company: " + allJobs.get(i).getCompany());
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, jobDisplay);
        rank_jobs_list.setAdapter(arrayAdapter);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offers_list);

        rank_jobs_list = (ListView) findViewById(R.id.listViewJobOffersList);
        cancelButtonJobOfferlist = (Button) findViewById(R.id.cancelButtonJobOffersList);
        compareButtonJobOffList = (Button) findViewById(R.id.compareButtonJobOffersList);

        mJobOffersListActivityViewModel = new ViewModelProvider(this).get(JobOffersListActivityViewModel.class);

        allJobs = mJobOffersListActivityViewModel.getJobsOrderByRankScore();
        loadJobsToListView();

        selectedJobs = new ArrayList<Job>();

        compareButtonJobOffList.setEnabled(false);

        rank_jobs_list.setChoiceMode(rank_jobs_list.CHOICE_MODE_MULTIPLE);
        rank_jobs_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectTwoJobsToCompare(view, i);
            }
        });

        cancelButtonJobOfferlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentJobScreen = new Intent(getBaseContext(), MainActivity.class);
                startActivity(currentJobScreen);
            }
        });

        compareButtonJobOffList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentJobScreen = new Intent(getBaseContext(), TwoJobsComparisonActivity.class);
                // Serializable Job
                currentJobScreen.putExtra("two_jobs_comparison", (ArrayList<Job>)selectedJobs);
                startActivity(currentJobScreen);
            }
        });

    }

}
