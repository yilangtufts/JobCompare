package edu.gatech.seclass.jobcompare6300;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.gatech.seclass.jobcompare6300.db.entity.Comparison;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;

public class JobTest {

    private Job job1 = new Job("sde", "gt", "atlanta", "GA", 2000, 100000.0, 10000.0, 5, 8, 10.0, true);
    //String title, String company, String city, String state, int costOfLiving, double salary, double bonus, double teleWork, double leave, double gymAllowance, boolean isCurrentJob) {

    @Test
    public void getJobId() {
    }

    @Test
    public void setJobId() {
        job1.setJobId(10001);
        int id = job1.getJobId();
        assertEquals(10001, id);
    }

    @Test
    public void getCompany() {
        String company = job1.getCompany();
        assertEquals("gt", company);
    }

    @Test
    public void setCompany() {
        job1.setCompany("omscs");
        String company  = job1.getCompany();
        assertEquals("omscs", company);
    }

    @Test
    public void getTitle() {
        String company = job1.getTitle();
        assertEquals("sde", company);
    }

    @Test
    public void setTitle() {
        job1.setTitle("sde2");
        String company  = job1.getTitle();
        assertEquals("sde2", company);
    }

    @Test
    public void getCity() {
        String company = job1.getCity();
        assertEquals("atlanta", company);
    }

    @Test
    public void setCity() {
        job1.setCity("savannah");
        String company  = job1.getCity();
        assertEquals("savannah", company);
    }

    @Test
    public void getState() {
        String company = job1.getState();
        assertEquals("GA", company);
    }

    @Test
    public void setState() {
        job1.setState("FL");
        String company  = job1.getState();
        assertEquals("FL", company);
    }

    @Test
    public void getCostOfLiving() {
        int company = job1.getCostOfLiving();
        assertEquals(2000, company);
    }

    @Test
    public void setCostOfLiving() {
        job1.setCostOfLiving(3000);
        int company  = job1.getCostOfLiving();
        assertEquals(3000, company);
    }

    @Test
    public void getSalary() {
        double company = job1.getSalary();
        assertEquals(100000.0, company, 0.0);
    }

    @Test
    public void setSalary() {
        job1.setSalary(200000.0);
        double company  = job1.getSalary();
        assertEquals(200000.0, company, 0.0);
    }

    @Test
    public void getBonus() {
        double company = job1.getBonus();
        assertEquals(10000.0, company, 0.0);
    }

    @Test
    public void setBonus() {
        job1.setBonus(20000.0);
        double company  = job1.getBonus();
        assertEquals(20000.0, company, 0.0);
    }

    @Test
    public void getTeleWork() {
        double company = job1.getTeleWork();
        assertEquals(5, company, 0.0);
    }

    @Test
    public void setTeleWork() {
        job1.setTeleWork(2);
        double company  = job1.getTeleWork();
        assertEquals(2, company, 0.0);
    }

    @Test
    public void getLeave() {
        double company = job1.getLeave();
        assertEquals(8, company, 0.0);
    }

    @Test
    public void setLeave() {
        job1.setLeave(10);
        double company  = job1.getLeave();
        assertEquals(10, company, 0.0);
    }

    @Test
    public void getGymAllowance() {
        double company = job1.getGymAllowance();
        assertEquals(10.0, company, 0.0);
    }

    @Test
    public void setGymAllowance() {
        job1.setGymAllowance(12.0);
        double company  = job1.getGymAllowance();
        assertEquals(12.0, company, 0.0);
    }

    @Test
    public void isCurrentJob() {
        boolean company = job1.isCurrentJob();
        assertEquals(true, company);
    }

    @Test
    public void setCurrentJob() {
        job1.setCurrentJob(false);
        boolean company  = job1.isCurrentJob();
        assertEquals(false, company);
    }

    @Test
    public void getAllJobs() {
    }

    private Job jobcurrent = new Job("sde", "amazon", "seattle", "WA", 197, 150000.0, 40000.0, 0, 10, 0, true);

    private Comparison comparison1 = new Comparison(1,
            1,
            1,
            1,
            1);

    @Test
    public void setWeightScoreCurrent() {
        jobcurrent.setWeightScore(comparison1);
        double score = jobcurrent.getWeightScore();
        assertEquals( 21778.60, score, 1.0);
        // 21778.60 is manually calculated number based on the equation
    }

    private Job job3 = new Job("mle", "amazon", "seattle", "WA", 197, 180000.0, 40000.0, 5, 12, 0, false);

    private Comparison comparison3 = new Comparison(1,
            1,
            2,
            5,
            1);

    @Test
    public void setWeightScore3() {
        job3.setWeightScore(comparison3);
        double score = job3.getWeightScore();
        assertEquals( 13276.06, score, 1.0);
        // 13276.06 is manually calculated number based on the equation
    }

    private Job job5 = new Job("sde2", "apple", "LA", "CA", 199, 160000.0, 55000.0, 3, 12, 100, false);

    private Comparison comparison5 = new Comparison(1,
            1,
            5,
            1,
            1);

    @Test
    public void setWeightScore5() {
        job5.setWeightScore(comparison5);
        double score = job5.getWeightScore();
        assertEquals( 14661.29, score, 1.0);
        // 14661.29 is manually calculated number based on the equation
    }
}