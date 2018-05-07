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
public class BindingsWithMeritTable {
    String Name,roll,totalMarks;
    int meritPos;

    public int getMeritPos() {
        return meritPos;
    }

    public void setMeritPos(int meritPos) {
        this.meritPos = meritPos;
    }

    public BindingsWithMeritTable(String Name, String roll, String totalMarks, int meritPos) {
        this.Name = Name;
        this.roll = roll;
        this.totalMarks = totalMarks;
        this.meritPos = meritPos;
    }
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }
    
}
