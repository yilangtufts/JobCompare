package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.seclass.jobcompare6300.db.entity.Comparison;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;
import edu.gatech.seclass.jobcompare6300.view.MainActivity;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    @Rule
    public ActivityTestRule<MainActivity> tActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    @Test // Test main menu
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("edu.gatech.seclass.jobcompare6300", appContext.getPackageName());
    }

    @Test // Test if current job details can be entered, edited, saved
    public void viewCurrentJobTester(){
        //open "view current job"
        onView(withId(R.id.viewCurrentJob)).perform(click());

        //type the job detail
        onView(withId(R.id.titleFieldJobOfferEditor)).perform(clearText(), replaceText("SDE1"));
        onView(withId(R.id.companyFieldJobOfferEditor)).perform(clearText(), replaceText("Amazon"));
        onView(withId(R.id.cityFieldJobOfferEditor)).perform(clearText(), replaceText("Seattle"));
        onView(withId(R.id.stateFieldJobOfferEditor)).perform(clearText(), replaceText("WA"));
        onView(withId(R.id.costOflivingFieldJobOfferEditor)).perform(clearText(), replaceText("197"));
        onView(withId(R.id.salaryFieldJobOfferEditor)).perform(clearText(), replaceText("150000.0"));
        onView(withId(R.id.bonusFieldJobOfferEditor)).perform(clearText(), replaceText("40000"));
        onView(withId(R.id.teleworkFieldJobOfferEditor)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.leaveFieldJobOfferEditor)).perform(clearText(), replaceText("10"));
        onView(withId(R.id.gymFieldJobOfferEditor)).perform(clearText(), replaceText("20"));
        Espresso.closeSoftKeyboard();

        //save
        onView(withId(R.id.saveButtonJobOfferEditor)).perform(click());

        //back to the main menu;
        Espresso.pressBack();

        //open "view curr job" again
        onView(withId(R.id.viewCurrentJob)).perform(click());

        // check whether the recent typed job detail is saved
        onView(withId(R.id.titleFieldJobOfferEditor)).check(matches(withText("SDE1")));
        onView(withId(R.id.companyFieldJobOfferEditor)).check(matches(withText("Amazon")));
        onView(withId(R.id.cityFieldJobOfferEditor)).check(matches(withText("Seattle")));
        onView(withId(R.id.stateFieldJobOfferEditor)).check(matches(withText("WA")));
        onView(withId(R.id.costOflivingFieldJobOfferEditor)).check(matches(withText("197")));
        onView(withId(R.id.salaryFieldJobOfferEditor)).check(matches(withText("150000.0")));
        onView(withId(R.id.bonusFieldJobOfferEditor)).check(matches(withText("40000.0")));
        onView(withId(R.id.teleworkFieldJobOfferEditor)).check(matches(withText("0")));
        onView(withId(R.id.leaveFieldJobOfferEditor)).check(matches(withText("10")));
        onView(withId(R.id.gymFieldJobOfferEditor)).check(matches(withText("20.0")));

    }


    @Test
    public void saveJobOfferTester(){

        // open view job offer
        onView(withId(R.id.viewJobOffers)).perform(click());

        // type the first job infos
        onView(withId(R.id.titleFieldJobOfferEditor)).perform(clearText(), replaceText("Senior SE"));
        onView(withId(R.id.companyFieldJobOfferEditor)).perform(clearText(), replaceText("Netflix"));
        onView(withId(R.id.cityFieldJobOfferEditor)).perform(clearText(), replaceText("Los Angeles"));
        onView(withId(R.id.stateFieldJobOfferEditor)).perform(clearText(), replaceText("CA"));
        onView(withId(R.id.costOflivingFieldJobOfferEditor)).perform(clearText(), replaceText("199"));
        onView(withId(R.id.salaryFieldJobOfferEditor)).perform(clearText(), replaceText("120000.0"));
        onView(withId(R.id.bonusFieldJobOfferEditor)).perform(clearText(), replaceText("100000.0"));
        onView(withId(R.id.teleworkFieldJobOfferEditor)).perform(clearText(), replaceText("4"));
        onView(withId(R.id.leaveFieldJobOfferEditor)).perform(clearText(), replaceText("10"));
        onView(withId(R.id.gymFieldJobOfferEditor)).perform(clearText(), replaceText("200.0"));

        Espresso.closeSoftKeyboard();

        // click save and click add another job
        onView(withId(R.id.saveButtonJobOfferEditor)).perform(click());
        onView(withId(R.id.addAnotherJobButtonJobOfferEditor)).perform(click());

        // type the second job infos
        onView(withId(R.id.titleFieldJobOfferEditor)).perform(clearText(), replaceText("SE"));
        onView(withId(R.id.companyFieldJobOfferEditor)).perform(clearText(), replaceText("Facebook"));
        onView(withId(R.id.cityFieldJobOfferEditor)).perform(clearText(), replaceText("Austin"));
        onView(withId(R.id.stateFieldJobOfferEditor)).perform(clearText(), replaceText("TX"));
        onView(withId(R.id.costOflivingFieldJobOfferEditor)).perform(clearText(), replaceText("162"));
        onView(withId(R.id.salaryFieldJobOfferEditor)).perform(clearText(), replaceText("130000.0"));
        onView(withId(R.id.bonusFieldJobOfferEditor)).perform(clearText(), replaceText("50000.0"));
        onView(withId(R.id.teleworkFieldJobOfferEditor)).perform(clearText(), replaceText("4"));
        onView(withId(R.id.leaveFieldJobOfferEditor)).perform(clearText(), replaceText("15"));
        onView(withId(R.id.gymFieldJobOfferEditor)).perform(clearText(), replaceText("300.0"));

        // save the second job
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButtonJobOfferEditor)).perform(click());

        // compare the second added job with the current job
        onView(withId(R.id.compareWithCurrentJobButtonJobOfferEditor)).perform(click());
        Espresso.pressBack();

        onView(withId(R.id.addAnotherJobButtonJobOfferEditor)).perform(click());

        // type the third job infos
        onView(withId(R.id.titleFieldJobOfferEditor)).perform(clearText(), replaceText("MLE"));
        onView(withId(R.id.companyFieldJobOfferEditor)).perform(clearText(), replaceText("Amazon"));
        onView(withId(R.id.cityFieldJobOfferEditor)).perform(clearText(), replaceText("Seattle"));
        onView(withId(R.id.stateFieldJobOfferEditor)).perform(clearText(), replaceText("WA"));
        onView(withId(R.id.costOflivingFieldJobOfferEditor)).perform(clearText(), replaceText("197"));
        onView(withId(R.id.salaryFieldJobOfferEditor)).perform(clearText(), replaceText("180000.0"));
        onView(withId(R.id.bonusFieldJobOfferEditor)).perform(clearText(), replaceText("40000.0"));
        onView(withId(R.id.teleworkFieldJobOfferEditor)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.leaveFieldJobOfferEditor)).perform(clearText(), replaceText("12"));
        onView(withId(R.id.gymFieldJobOfferEditor)).perform(clearText(), replaceText("0"));

        // save the third job
        Espresso.closeSoftKeyboard();

        // press cancel button
        onView(withId(R.id.cancelButtonJobOfferEditor)).perform(click());

    }


    @Test
    public void viewComparisonSettingTester(){
        // open comparison setting first
        onView(withId(R.id.comparisonSettings)).perform(click());

        // type input values
        onView(withId(R.id.yearlySalaryText)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.yearlyBonusText)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.weeklyTeleworkText)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.leaveTimeText)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.gymAllowanceText)).perform(clearText(), replaceText("1"));

        Espresso.closeSoftKeyboard();

        // click cancel
        onView(withId(R.id.cancelButtonComparisonSettings)).perform(click());

        // open comparison setting again
        onView(withId(R.id.comparisonSettings)).perform(click());

        // type new input values
        onView(withId(R.id.yearlySalaryText)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.yearlyBonusText)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.weeklyTeleworkText)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.leaveTimeText)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.gymAllowanceText)).perform(clearText(), replaceText("3"));

        Espresso.closeSoftKeyboard();

        // save it
        onView(withId(R.id.saveButtonComparisonSettings)).perform(click());

        // open comparison setting again
        onView(withId(R.id.comparisonSettings)).perform(click());

        // check whether the most recent input is saved
        onView(withId(R.id.yearlySalaryText)).check(matches(withText("2")));
        onView(withId(R.id.yearlyBonusText)).check(matches(withText("1")));;
        onView(withId(R.id.weeklyTeleworkText)).check(matches(withText("3")));
        onView(withId(R.id.leaveTimeText)).check(matches(withText("2")));
        onView(withId(R.id.gymAllowanceText)).check(matches(withText("3")));

    }

    @Test
    public void viewCompareJobTester(){
        onView(withId(R.id.compareJobOffers)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.cancelButtonJobOffersList)).perform(click());
    }

}