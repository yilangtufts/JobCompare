package edu.gatech.seclass.jobcompare6300.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.db.entity.Comparison;
import edu.gatech.seclass.jobcompare6300.viewmodel.ComparisonsSettingsViewModel;
import edu.gatech.seclass.jobcompare6300.viewmodel.JobOfferEditorViewModel;

public class ComparisonSettingsActivity extends AppCompatActivity {

    private Button cancelButton, saveButton;
    private EditText  yearlySalary, yearlyBonus, weeklyTelework, leaveTime, gymAllowance;;
    private Comparison curComparison;
    private ComparisonsSettingsViewModel mComparisonsSettingsViewModel;

    // validate user inputs: all fields should not be empty,
    public void validateInput() {
        try {

            String salaryString = yearlySalary.getText().toString();
            try {
                Integer salaryValue = Integer.parseInt(salaryString);
                if (salaryString.isEmpty() || salaryValue <= 0) {
                    yearlySalary.setError("Invalid yearly salary ");
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
        setContentView(R.layout.activity_comparison_settings);

        mComparisonsSettingsViewModel = new ViewModelProvider(this).get(ComparisonsSettingsViewModel.class);

        cancelButton = (Button) findViewById(R.id.cancelButtonComparisonSettings);
        saveButton = (Button) findViewById(R.id.saveButtonComparisonSettings);
        yearlySalary = (EditText) findViewById(R.id.yearlySalaryText);
        yearlyBonus = (EditText) findViewById(R.id.yearlyBonusText);
        weeklyTelework = (EditText) findViewById(R.id.weeklyTeleworkText);
        leaveTime = (EditText) findViewById(R.id.leaveTimeText);
        gymAllowance = (EditText) findViewById(R.id.gymAllowanceText);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                //
                curComparison = mComparisonsSettingsViewModel.getCurrentComparison();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if( curComparison != null) {
                            //load the latest comparison settings in db
                            yearlySalary.setText(String.valueOf(curComparison.getSalaryWeight()));
                            yearlyBonus.setText(String.valueOf(curComparison.getBonusWeight()));
                            weeklyTelework.setText(String.valueOf(curComparison.getTeleworkWeight()));
                            leaveTime.setText(String.valueOf(curComparison.getLeaveWeight()));
                            gymAllowance.setText(String.valueOf(curComparison.getGymAllowanceWeight()));
                        }
                    }
                });
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            /*
            Set all fields back to what's in DB
             */

            @Override
            public void onClick(View view) {
                Intent currentJobScreen = new Intent(getBaseContext(), MainActivity.class);
                startActivity(currentJobScreen);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
           /*
           Error check the inputs
           Add to DB
            */

            @Override
            public void onClick(View view) {
                // validate input here.

                //
                try {
                    validateInput();

                    Integer yearlySalaryInt = Integer.parseInt(yearlySalary.getText().toString());
                    Integer yearlyBonusInt = Integer.parseInt(yearlyBonus.getText().toString());
                    Integer teleworkInt = Integer.parseInt(weeklyTelework.getText().toString());
                    Integer leaveInt = Integer.parseInt(leaveTime.getText().toString());
                    Integer gymAllowanceInt = Integer.parseInt(gymAllowance.getText().toString());

                    Comparison newCurComparison = new Comparison(yearlySalaryInt, yearlyBonusInt, teleworkInt, leaveInt, gymAllowanceInt);
                    // save to DB
                    mComparisonsSettingsViewModel.saveComparisonsSettings(newCurComparison);

                } catch(Exception e) {
                    System.out.println("Error message: " + e.getMessage());
                }

                Intent currentJobScreen = new Intent(getBaseContext(), MainActivity.class);
                startActivity(currentJobScreen);
            }
        });
    }
}