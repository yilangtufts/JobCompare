package edu.gatech.seclass.jobcompare6300;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.gatech.seclass.jobcompare6300.db.entity.Comparison;

public class ComparisonTest {

    private Comparison comparison1 = new Comparison(30,
            10,
            5,
            25,
            20);

    @Test
    public void getComparisonId() {
        //int id = comparison1.getComparisonId();
        //System.out.println(id);
    }

    @Test
    public void setComparisonId() {
        comparison1.setComparisonId(10003);
        int id = comparison1.getComparisonId();
        assertEquals(10003, id);
    }

    @Test
    public void getSalaryWeight() {
        int weight  = comparison1.getSalaryWeight();
        assertEquals(30, weight);
    }

    @Test
    public void setSalaryWeight() {
        comparison1.setSalaryWeight(40);
        int weight  = comparison1.getSalaryWeight();
        assertEquals(40, weight);
    }

    @Test
    public void getBonusWeight() {
        int weight  = comparison1.getBonusWeight();
        assertEquals(10, weight);
    }

    @Test
    public void setBonusWeight() {
        comparison1.setBonusWeight(20);
        int weight  = comparison1.getBonusWeight();
        assertEquals(20, weight);
    }

    @Test
    public void getTeleworkWeight() {
        int weight  = comparison1.getTeleworkWeight();
        assertEquals(5, weight);
    }

    @Test
    public void setTeleworkWeight() {
        comparison1.setTeleworkWeight(8);
        int weight  = comparison1.getTeleworkWeight();
        assertEquals(8, weight);
    }

    @Test
    public void getLeaveWeight() {
        int weight  = comparison1.getLeaveWeight();
        assertEquals(25, weight);
    }

    @Test
    public void setLeaveWeight() {
        comparison1.setLeaveWeight(20);
        int weight  = comparison1.getLeaveWeight();
        assertEquals(20, weight);
    }

    @Test
    public void getGymAllowanceWeight() {
        int weight  = comparison1.getGymAllowanceWeight();
        assertEquals(20, weight);
    }

    @Test
    public void setGymAllowanceWeight() {
        comparison1.setGymAllowanceWeight(10);
        int weight  = comparison1.getGymAllowanceWeight();
        assertEquals(10, weight);
    }
}