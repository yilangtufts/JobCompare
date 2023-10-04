package edu.gatech.seclass.jobcompare6300.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.*;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;
import edu.gatech.seclass.jobcompare6300.viewmodel.JobOfferEditorViewModel;

public class JobOfferEditorActivity extends AppCompatActivity {

    private Button cancelButton, saveButton, enterAnotherJobButton, compareCurrentJobButton;
    private EditText title, company, city, state, costOfLiving, yearlySalary, yearlyBonus, weeklyTelework, leaveTime, gymAllowance;

    private JobOfferEditorViewModel mJobOfferEditorViewModel;
    private Job tmpJobOffer;
    private Job currentJob;

    private void reSetAllEditTexts() {
        title.getText().clear();
        company.getText().clear();
        city.getText().clear();
        state.getText().clear();
        costOfLiving.getText().clear();
        yearlySalary.getText().clear();
        yearlyBonus.getText().clear();
        weeklyTelework.getText().clear();
        leaveTime.getText().clear();
        gymAllowance.getText().clear();
    }
    public void validateInput() {
        try {

            String titleString = title.getText().toString();
            if (titleString.isEmpty()) {
                title.setError("Invalid title");
            }

            String companyString = company.getText().toString();
            if (companyString.isEmpty()) {
                company.setError("Invalid company");
            }

            String cityString = city.getText().toString();
            if (cityString.isEmpty()) {
                city.setError("Invalid city");
            }

            String stateString = city.getText().toString();
            if (stateString.isEmpty()) {
                state.setError("Invalid state");
            }

            String costOfLivingString = costOfLiving.getText().toString();
            try {
                int costOfLivingInt = Integer.parseInt(costOfLivingString);
                if (costOfLivingString.isEmpty() || costOfLivingInt <= 0) {
                    costOfLiving.setError("Invalid cost of living");
                }
            } catch (Exception e) {
                costOfLiving.setError("Invalid cost of living");
            }


            String salaryString = yearlySalary.getText().toString();
            try {
                double salaryValue = Double.parseDouble(salaryString);
                if (salaryString.isEmpty() || salaryValue <= 0.0) {
                    yearlySalary.setError("Invalid yearly salary");
                }
            } catch (Exception e){
                yearlySalary.setError("Invalid yearly salary");
            }

            String bonusString = yearlyBonus.getText().toString();
            try {
                double bonusValue = Double.parseDouble(bonusString);
                if (bonusString.isEmpty() || bonusValue <= 0.0) {
                    yearlyBonus.setError("Invalid yearly bonus");
                }
            } catch( Exception e) {
                yearlyBonus.setError("Invalid yearly bonus");
            }
            try {
                String teleworkString = weeklyTelework.getText().toString();
                Integer teleworkValue = Integer.parseInt(teleworkString);
                if (teleworkString.isEmpty() || teleworkValue < 0 || teleworkValue > 5) {
                    weeklyTelework.setError("Invalid weekly telework");
                }
            } catch( Exception e) {
                weeklyTelework.setError("Invalid weekly telework");
            }

            String leaveString = leaveTime.getText().toString();
            try {
                Integer leaveValue = Integer.parseInt(leaveString);
                if (leaveString.isEmpty() || leaveValue < 0 || leaveValue > 260) {
                    leaveTime.setError("Invalid leave time");
                }
            } catch (Exception e) {
                leaveTime.setError("Invalid leave time");
            }

            String gymString = gymAllowance.getText().toString();
            try {
                double gymValue = Double.parseDouble(gymString);
                if (gymString.isEmpty() || gymValue < 0.0 || gymValue > 500.0) {
                    gymAllowance.setError("Invalid gym membership allowance");
                }
            } catch (Exception e) {
                gymAllowance.setError("Invalid gym membership allowance");
            }

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mJobOfferEditorViewModel = new ViewModelProvider(this).get(JobOfferEditorViewModel.class);

        setContentView(R.layout.activity_job_offer_editor);


        title = (EditText) findViewById(R.id.titleFieldJobOfferEditor);
        company = (EditText) findViewById(R.id.companyFieldJobOfferEditor);
        city = (EditText) findViewById(R.id.cityFieldJobOfferEditor);
        state = (EditText) findViewById(R.id.stateFieldJobOfferEditor);
        costOfLiving = (EditText) findViewById(R.id.costOflivingFieldJobOfferEditor);
        yearlySalary = (EditText) findViewById(R.id.salaryFieldJobOfferEditor);
        yearlyBonus = (EditText) findViewById(R.id.bonusFieldJobOfferEditor);
        weeklyTelework = (EditText) findViewById(R.id.teleworkFieldJobOfferEditor);
        leaveTime = (EditText) findViewById(R.id.leaveFieldJobOfferEditor);
        gymAllowance = (EditText) findViewById(R.id.gymFieldJobOfferEditor);

        cancelButton = (Button) findViewById(R.id.cancelButtonJobOfferEditor);
        saveButton = (Button) findViewById(R.id.saveButtonJobOfferEditor);
        enterAnotherJobButton = (Button) findViewById(R.id.addAnotherJobButtonJobOfferEditor);
        compareCurrentJobButton = (Button) findViewById(R.id.compareWithCurrentJobButtonJobOfferEditor);

        //clear all editTexts
        reSetAllEditTexts();

        //Have to save this job offer to be able to enter another job
        enterAnotherJobButton.setEnabled(false);

        compareCurrentJobButton.setEnabled(false);

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent currentJobScreen = new Intent(getBaseContext(), MainActivity.class);
                startActivity(currentJobScreen);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                validateInput();
                try {
                    String titleStr = title.getText().toString();
                    String companyStr = company.getText().toString();
                    String cityStr = city.getText().toString();
                    String stateStr = state.getText().toString();
                    Integer costOfLivingInt = Integer.parseInt(costOfLiving.getText().toString());
                    Double yearlySalaryDouble = Double.parseDouble(yearlySalary.getText().toString());
                    Double bonusDouble = Double.parseDouble(yearlyBonus.getText().toString());
                    Integer teleWorkInt = Integer.parseInt(weeklyTelework.getText().toString());
                    Integer leaveInt = Integer.parseInt(leaveTime.getText().toString());
                    Double gymDouble = Double.parseDouble(gymAllowance.getText().toString());

                    //Save temp job offer to DB
                    tmpJobOffer = new Job(titleStr, companyStr, cityStr, stateStr, costOfLivingInt, yearlySalaryDouble, bonusDouble, teleWorkInt, leaveInt, gymDouble, false);
                    mJobOfferEditorViewModel.addJobOffer(tmpJobOffer);

                    //After saving, we should enable enterAnotherJob button.
                    saveButton.setEnabled(false);
                    enterAnotherJobButton.setEnabled(true);

                    // check if currentJob is available or not
                    currentJob = mJobOfferEditorViewModel.getCurrentJob();
                    if (currentJob != null) {
                        compareCurrentJobButton.setEnabled(true);
                    }
                } catch (Exception e) {
                    System.out.println("Error message: " + e.getMessage());
                }
            }
        });

        compareCurrentJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                // compare this job with currentJob if available
                Intent currentJobScreen = new Intent(getBaseContext(), TwoJobsComparisonActivity.class);
                List<Job> selectedJobs = new ArrayList<Job>();
                selectedJobs.add(tmpJobOffer);
                selectedJobs.add(currentJob);
                // Serializable Job
                currentJobScreen.putExtra("two_jobs_comparison", (ArrayList<Job>)selectedJobs);
                startActivity(currentJobScreen);
            }
        });

        enterAnotherJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                // clear out all job-offer fields
                reSetAllEditTexts();

                compareCurrentJobButton.setEnabled(false);
                saveButton.setEnabled(true);
                enterAnotherJobButton.setEnabled(false);
            }
        });

    }
}