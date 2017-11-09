/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admindashboard;

import com.sms.entity.Admin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import pckgdatabase.AdminDBUtils;

/**
 * FXML Controller class
 *
 * @author ashmeet
 */
public class AdminController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtSecurityAnswer;

    private Admin admin;
    private AdminDBUtils adminDBUtils;
    @FXML
    private ComboBox<String> cmbSecurityQuestions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("What is your favorite food ?", "What is your Favorite Sports ?");
        cmbSecurityQuestions.setItems(list);
    }

    @FXML
    private void handleStudentManagementAction(ActionEvent event) {
    }

    @FXML
    private void handleStudentDetailsAction(ActionEvent event) {
    }

    @FXML
    private void handleQuestionManagementAction(ActionEvent event) {
    }

    @FXML
    private void handleQuestionListsAction(ActionEvent event) {
    }

    @FXML
    private void hanldeResultAction(ActionEvent event) {
    }

    @FXML
    private void hanldeReportAction(ActionEvent event) {
    }

    @FXML
    private void handleClearAction(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        admin = new Admin();
        adminDBUtils = new AdminDBUtils();
        String securityQuestion=cmbSecurityQuestions.getSelectionModel().getSelectedItem();
        

        admin.setAdminName(txtName.getText());
        admin.setPassword(txtPassword.getText());
        admin.setSecurityQuestion(securityQuestion);
        admin.setSecurityAnswer(txtSecurityAnswer.getText());

        if (adminDBUtils.createAdmin(admin)) {
            System.out.println("Admin inserted Successfully");
            clear();
        }

    }

    @FXML
    private void hanldeUpdateAction(ActionEvent event) {
    }

    @FXML
    private void hanldeDeleteAction(ActionEvent event) {
    }

    
    private void clear(){
        txtId.clear();
        txtName.clear();
        txtPassword.clear();
        txtSecurityAnswer.clear();
        cmbSecurityQuestions.getSelectionModel().clearSelection();
    }
}
