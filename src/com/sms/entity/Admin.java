/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.entity;

import java.io.FileInputStream;
import javafx.scene.image.Image;
import javax.imageio.stream.FileImageInputStream;

/**
 *
 * @author ashmeet
 */
public class Admin {

    private int id;
    private String adminName;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private FileInputStream fis;
    private Image img;

    public Admin() {

    }

    public Admin(int id, String adminName, String password, String securityQuestion, String securityAnswer) {
        this.id=id;
        this.adminName = adminName;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }

  
}
