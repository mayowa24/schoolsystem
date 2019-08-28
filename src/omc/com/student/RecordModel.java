/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omc.com.student;

/**
 *
 * @author HP-PC
 */
public class RecordModel {
    String name,studentid,gender,year,parentname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public RecordModel(String name, String studentid, String gender, String year, String parentname) {
        this.name = name;
        this.studentid = studentid;
        this.gender = gender;
        this.year = year;
        this.parentname = parentname;
    }

    @Override
    public String toString() {
        return "RecordModel{" + "name=" + name + ", studentid=" + studentid + ", gender=" + gender + ", year=" + year + ", parentname=" + parentname + '}';
    }

    
    
    
    
}
