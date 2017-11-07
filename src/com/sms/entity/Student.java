
package com.sms.entity;

import java.util.Date;

/**
 *
 * @author ashmeet
 */
public class Student {
    private int id;
    private String Namel;
    private String address;
    private int sem;
    private int roll;
    private String username;
    private String password;
    private Date date;
    private String dob;

    public Student(int id, String Namel, String address, int sem, int roll, String username, String password, Date date, String dob) {
        this.id = id;
        this.Namel = Namel;
        this.address = address;
        this.sem = sem;
        this.roll = roll;
        this.username = username;
        this.password = password;
        this.date = date;
        this.dob = dob;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamel() {
        return Namel;
    }

    public void setNamel(String Namel) {
        this.Namel = Namel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    
    
}
