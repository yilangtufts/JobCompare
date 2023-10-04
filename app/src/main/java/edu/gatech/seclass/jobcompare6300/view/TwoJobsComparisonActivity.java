package edu.gatech.seclass.jobcompare6300.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.*;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;
import edu.gatech.seclass.jobcompare6300.viewmodel.TwoJobsComparisonViewModel;

public class TwoJobsComparisonActivity extends AppCompatActivity {

    private Button cancelButton, compareOthersButton;
    private ArrayList<Job> selectedJobs;
    private TwoJobsComparisonViewModel mTwoJobsComparisonViewModel;
    private TextView title_job1, title_job2, company_job1, company_job2, city_job1, city_job2, state_job1, state_job2, adjustSalary_job1, adjustSalary_job2, adjustBonus_job1, adjustBonus_job2, telework_job1, telework_job2, leave_job1, leave_job2, gym_job1, gym_job2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_jobs_comparison);

        cancelButton = (Button) findViewById(R.id.cancelButtonJobComparison);
        compareOthersButton = (Button) findViewById(R.id.anotherComparisonButtonJobComparison);

        title_job1 = (TextView) findViewById(R.id.jobComparisonJobOneTitle);
        title_job2 = (TextView) findViewById(R.id.jobComparisonJobTwoTitle);
        company_job1 = (TextView) findViewById(R.id.jobComparisonJobOneCompany);
        company_job2 = (TextView) findViewById(R.id.jobComparisonJobTwoCompany);
        city_job1 = (TextView) findViewById(R.id.jobComparisonJobOneCity);
        city_job2 = (TextView) findViewById(R.id.jobComparisonJobTwoCity);
        state_job1 = (TextView) findViewById(R.id.jobComparisonJobOneState);
        state_job2 = (TextView) findViewById(R.id.jobComparisonJobTwoState);
        adjustSalary_job1 = (TextView) findViewById(R.id.jobComparisonJobOneYearlySalary);
        adjustSalary_job2 = (TextView) findViewById(R.id.jobComparisonJobTwoYearlySalary);
        adjustBonus_job1 = (TextView) findViewById(R.id.jobComparisonJobOneYearlyBonus);
        adjustBonus_job2 = (TextView) findViewById(R.id.jobComparisonJobTwoYearlyBonus);
        telework_job1 = (TextView) findViewById(R.id.jobComparisonJobOneTeleworkDays);
        telework_job2 = (TextView) findViewById(R.id.jobComparisonJobTwoTeleworkDays);
        leave_job1 = (TextView) findViewById(R.id.jobComparisonJobOneLeaveTime);
        leave_job2 = (TextView) findViewById(R.id.jobComparisonJobTwoLeaveTime);
        gym_job1 = (TextView) findViewById(R.id.jobComparisonJobOneGymAllowance);
        gym_job2 = (TextView) findViewById(R.id.jobComparisonJobTwoGymAllowance);

        selectedJobs = (ArrayList<Job>) getIntent().getSerializableExtra("two_jobs_comparison");

        mTwoJobsComparisonViewModel = new ViewModelProvider(this).get(TwoJobsComparisonViewModel.class);
        //mTwoJobsComparisonViewModel.adjustForCostOfLiving(selectedJobs);

        //Display details
        title_job1.setText(selectedJobs.get(0).getTitle());
        title_job2.setText(selectedJobs.get(1).getTitle());

        company_job1.setText(selectedJobs.get(0).getCompany());
        company_job2.setText(selectedJobs.get(1).getCompany());

        city_job1.setText(selectedJobs.get(0).getCity());
        city_job2.setText(selectedJobs.get(1).getCity());

        state_job1.setText(selectedJobs.get(0).getState());
        state_job2.setText(selectedJobs.get(1).getState());

        //adjust salary
        DecimalFormat dFormat = new DecimalFormat("####,###,###.00");
        Double adjustedSalary_1 = selectedJobs.get(0).getSalary() / selectedJobs.get(0).getCostOfLiving() * 100;
        adjustSalary_job1.setText("$" + dFormat.format(adjustedSalary_1));
        Double adjustedSalary_2 = selectedJobs.get(1).getSalary() / selectedJobs.get(1).getCostOfLiving() * 100;
        adjustSalary_job2.setText("$" + dFormat.format(adjustedSalary_2));

        Double adjustedBonus_1 = selectedJobs.get(0).getBonus() / selectedJobs.get(0).getCostOfLiving() * 100;
        adjustBonus_job1.setText("$" + dFormat.format(adjustedBonus_1));
        Double adjustedBonus_2 = selectedJobs.get(1).getBonus() / selectedJobs.get(1).getCostOfLiving() * 100;
        adjustBonus_job2.setText("$" + dFormat.format(adjustedBonus_2));

        telework_job1.setText(String.valueOf(selectedJobs.get(0).getTeleWork()));
        telework_job2.setText(String.valueOf(selectedJobs.get(1).getTeleWork()));

        leave_job1.setText(String.valueOf(selectedJobs.get(0).getLeave()));
        leave_job2.setText(String.valueOf(selectedJobs.get(1).getLeave()));

        gym_job1.setText("$" + dFormat.format(selectedJobs.get(0).getGymAllowance()));
        gym_job2.setText("$" + dFormat.format(selectedJobs.get(1).getGymAllowance()));

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentJobScreen = new Intent(getBaseContext(), MainActivity.class);
                startActivity(currentJobScreen);
            }
        });

        compareOthersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentJobScreen = new Intent(getBaseContext(), JobOffersListActivity.class);
                startActivity(currentJobScreen);
            }
        });
    }
}