package edu.gatech.seclass.jobcompare6300.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;
import edu.gatech.seclass.jobcompare6300.viewmodel.CurrentJobEditorViewModel;

public class CurrentJobEditorActivity extends AppCompatActivity {
    private static final String TAG = "CurrentJobEditsActivity";

    private CurrentJobEditorViewModel mCurrentJobEditorViewModel;
    private Button cancelButton, saveButton;
    private Job tempJob;

    //EditText variables
    private EditText title, company, city, state, costOfLiving, yearlySalary, yearlyBonus, weeklyTelework, leaveTime, gymAllowance;

    // validate user inputs: all fields should not be empty,
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
        setContentView(R.layout.activity_current_job_editor);

        mCurrentJobEditorViewModel = new ViewModelProvider(this).get(CurrentJobEditorViewModel.class);

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


        saveButton = (Button) findViewById(R.id.saveButtonJobOfferEditor);
        cancelButton = (Button) findViewById(R.id.cancelButtonJobOfferEditor);

        // Run in Ui thread instead of main thread
        AsyncTask.execute(new Runnable() {
              @Override
              public void run() {

                  tempJob = mCurrentJobEditorViewModel.getCurrentJob();
                  runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          if (tempJob != null) {
                              //Display currentJob details
                              title.setText(tempJob.getTitle());
                              company.setText(tempJob.getCompany());
                              city.setText(tempJob.getCity());
                              state.setText(tempJob.getState());
                              costOfLiving.setText(String.valueOf(tempJob.getCostOfLiving()));
                              yearlySalary.setText(String.valueOf(tempJob.getSalary()));
                              yearlyBonus.setText(String.valueOf(tempJob.getBonus()));
                              weeklyTelework.setText(String.valueOf(tempJob.getTeleWork()));
                              leaveTime.setText(String.valueOf(tempJob.getLeave()));
                              gymAllowance.setText(String.valueOf(tempJob.getGymAllowance()));
                          }
                      }
                  });
              }
          });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                Clear all fields
                */

                Intent currentJobScreen = new Intent(getBaseContext(), MainActivity.class);
                startActivity(currentJobScreen);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check for errors in inputs
                Log.i(TAG, "before validate");
                validateInput();
                Log.i(TAG, "after validate");
                AsyncTask.execute(new Runnable() {
                      @Override
                      public void run() {
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

                              if (tempJob != null) {
                                  mCurrentJobEditorViewModel.deleteCurrentJob(tempJob);
                              }
                              // there is no currentJob in DB
                              Job tmpCurrentJob = new Job(titleStr, companyStr, cityStr, stateStr, costOfLivingInt, yearlySalaryDouble, bonusDouble, teleWorkInt, leaveInt, gymDouble, true);
                              mCurrentJobEditorViewModel.addNewCurrentJob(tmpCurrentJob);

                          } catch (Exception e) {
                              System.out.println("Error message: " + e.getMessage());
                          }
                      }
                  });

            }
        });
    }
}