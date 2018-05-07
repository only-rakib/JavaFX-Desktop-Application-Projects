/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

/**
 *
 * @author Rakib
 */
public class StudentMarks {
    private String roll;
    private double totalMarks;

    public StudentMarks(String roll, double totalMarks) {
        this.roll = roll;
        this.totalMarks = totalMarks;
    }
    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }
}
