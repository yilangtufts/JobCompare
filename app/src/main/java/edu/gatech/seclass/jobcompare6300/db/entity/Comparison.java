package edu.gatech.seclass.jobcompare6300.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.*;

@Entity(tableName = "comparison_table")
public class Comparison implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int comparisonId;

    @ColumnInfo(defaultValue = "1")
    private int salaryWeight;

    @ColumnInfo(defaultValue = "1")
    private int bonusWeight;

    @ColumnInfo(defaultValue = "1")
    private int teleworkWeight;

    @ColumnInfo(defaultValue = "1")
    private int leaveWeight;

    @ColumnInfo(defaultValue = "1")
    private int gymAllowanceWeight;

    public Comparison(int salaryWeight, int bonusWeight, int teleworkWeight, int leaveWeight, int gymAllowanceWeight) {
        this.salaryWeight = salaryWeight;
        this.bonusWeight = bonusWeight;
        this.teleworkWeight = teleworkWeight;
        this.leaveWeight = leaveWeight;
        this.gymAllowanceWeight = gymAllowanceWeight;
     }


    public int getComparisonId() {
        return comparisonId;
    }

    public void setComparisonId(int comparisonId) {
        this.comparisonId = comparisonId;
    }

    public int getSalaryWeight() {
        return salaryWeight;
    }

    public void setSalaryWeight(int salaryWeight) {
        this.salaryWeight = salaryWeight;
    }

    public int getBonusWeight() {
        return bonusWeight;
    }

    public void setBonusWeight(int bonusWeight) {
        this.bonusWeight = bonusWeight;
    }

    public int getTeleworkWeight() {
        return teleworkWeight;
    }

    public void setTeleworkWeight(int teleworkWeight) {
        this.teleworkWeight = teleworkWeight;
    }

    public int getLeaveWeight() {
        return leaveWeight;
    }

    public void setLeaveWeight(int leaveWeight) {
        this.leaveWeight = leaveWeight;
    }

    public int getGymAllowanceWeight() {
        return gymAllowanceWeight;
    }

    public void setGymAllowanceWeight(int gymAllowanceWeight) {
        this.gymAllowanceWeight = gymAllowanceWeight;
    }
}
