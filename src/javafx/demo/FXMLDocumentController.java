/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.demo;

import pckgdatabase.StudentDBUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pckgcommon.Common;
import pckgdatabase.AdminDBUtils;

/**
 *
 * @author ashmeet
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtPassword;
    @FXML
    private ComboBox<String> comboLoginType;
    
    StudentDBUtils studentDBUtils;
    AdminDBUtils adminDBUtils;
    Common common;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list=FXCollections.observableArrayList("admin","user");
        comboLoginType.setItems(list);
       
        
    }    

    @FXML
    private void handleLoginButtonPressed(ActionEvent event) throws IOException {
       studentDBUtils=new StudentDBUtils();
       adminDBUtils=new AdminDBUtils();
       String userType=comboLoginType.getSelectionModel().getSelectedItem();
       if(userType.equals("admin")){
           if(adminDBUtils.loginValidation(txtUsername.getText(), txtPassword.getText())){
               common=new Common();
               common.nextStage("/admindashboard/Admin.fxml", "Admin Window ", true);
               
               Stage current= (Stage) txtUsername.getScene().getWindow();
               current.hide();
               
               
           }else{
               Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("LOGIN VALIDATION ");
               alert.setHeaderText("Login Insuccesffull ");
               alert.setContentText("Your username and password do not match ");
               alert.showAndWait();
               
           }
       }else{
           if(studentDBUtils.loginValidation(txtUsername.getText(), txtPassword.getText())){
               common=new Common();
               common.nextStage("/admindashboard/Admin.fxml", "Student Window ", true);
               
               Stage current= (Stage) txtUsername.getScene().getWindow();
               current.hide();
               
               
               System.out.println("Login Successful");
           }else{
               System.out.println("Login insuccessful");
           }
       }
    }
    
}
