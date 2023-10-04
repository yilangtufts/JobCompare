package edu.gatech.seclass.jobcompare6300.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.*;

@Entity(tableName = "job_table")
public class Job implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int jobId;

    private String title;
    private String company;
    private String city;
    private String state;
    private int costOfLiving;
    private double salary;
    private double bonus;
    private int teleWork;
    private int leave;
    private double gymAllowance;
    private boolean isCurrentJob;

    private double weightScore;

    public Job() {

    }

    public Job(String title, String company, String city, String state, int costOfLiving, double salary, double bonus, int teleWork, int leave, double gymAllowance, boolean isCurrentJob) {
        this.title = title;
        this.company = company;
        this.city = city;
        this.state = state;
        this.costOfLiving = costOfLiving;
        this.salary = salary;
        this.bonus = bonus;
        this.teleWork = teleWork;
        this.leave = leave;
        this.gymAllowance = gymAllowance;
        this.isCurrentJob = isCurrentJob;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCostOfLiving() {
        return costOfLiving;
    }

    public void setCostOfLiving(int costOfLiving) {
        this.costOfLiving = costOfLiving;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public int getTeleWork() {
        return teleWork;
    }

    public void setTeleWork(int teleWork) {
        this.teleWork = teleWork;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    public double getGymAllowance() {
        return gymAllowance;
    }

    public void setGymAllowance(double gymAllowance) {
        this.gymAllowance = gymAllowance;
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }

    public double getWeightScore() {
        return weightScore;
    }

    public void setWeightScore(double weightScore) {
        this.weightScore = weightScore;
    }

    public void setWeightScore(Comparison comparison) {
        int totalWeight = comparison.getSalaryWeight() + comparison.getBonusWeight() + comparison.getTeleworkWeight() + comparison.getLeaveWeight() + comparison.getGymAllowanceWeight();
        if (totalWeight == 0) {
            System.out.println("Reset the weights!");
        }

        int costOfLiving = this.costOfLiving;
        if (costOfLiving == 0) {
            System.out.println("Enter the cost of living!");
        }

        double salary = this.salary / costOfLiving * 100;
        double bonus = this.bonus / costOfLiving * 100;
        double teleWork = (260.0 - 52 * this.teleWork) * (salary / 260.0) / 8.0;
        double leave = this.leave * salary / 260.0;
        double gymAllowance = this.gymAllowance;

        double jobScore = (comparison.getSalaryWeight() * salary +
                comparison.getBonusWeight() * bonus +
                comparison.getTeleworkWeight() * teleWork +
                comparison.getLeaveWeight() * leave +
                comparison.getGymAllowanceWeight() * gymAllowance)/totalWeight;

        this.weightScore = jobScore;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}