/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admindashboard;

import com.sms.entity.Admin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pckgcommon.Common;
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

    @FXML
    private ComboBox<String> cmbSecurityQuestions;
    @FXML
    private GridPane cmbSecurityQuestion;
    @FXML
    private TableColumn<Admin, Integer> colId;
    @FXML
    private TableColumn<Admin, String> colAdminName;
    @FXML
    private TableColumn<Admin, String> colPassword;
    @FXML
    private TableColumn<Admin, String> colSecurityQuestion;
    @FXML
    private TableColumn<Admin, String> colSecurityAnswer;
    @FXML
    private TableView<Admin> tblAdminData;

    private Admin admin;
    private AdminDBUtils adminDBUtils;
    ObservableList<Admin> listOfAdmin;
    @FXML
    private Label lblShow;

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
        String securityQuestion = cmbSecurityQuestions.getSelectionModel().getSelectedItem();

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
        Integer id = Integer.parseInt(txtId.getText());

    }

    private void clear() {
        txtId.clear();
        txtName.clear();
        txtPassword.clear();
        txtSecurityAnswer.clear();
        cmbSecurityQuestions.getSelectionModel().clearSelection();
    }

    @FXML
    private void hanldeLoadAction(ActionEvent event) {
        
        admin = new Admin();
        adminDBUtils = new AdminDBUtils();
        listOfAdmin = adminDBUtils.fetchData();
        if ((listOfAdmin) != null) {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colAdminName.setCellValueFactory(new PropertyValueFactory<>("adminName"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            colSecurityQuestion.setCellValueFactory(new PropertyValueFactory<>("securityQuestion"));
            colSecurityAnswer.setCellValueFactory(new PropertyValueFactory<>("securityAnswer"));

            tblAdminData.setItems(listOfAdmin);

        } else {
            System.out.println("No data found ");
        }
    }

    @FXML
    private void hanldeMouseClickeAction(MouseEvent event) {
        showTableDataOnFields();

    }

    void showTableDataOnFields() {
        Admin admin = (Admin) tblAdminData.getSelectionModel().getSelectedItem();
        txtId.setText("" + admin.getId());
        txtName.setText(admin.getAdminName());
        txtPassword.setText(admin.getPassword());
        cmbSecurityQuestions.setValue(admin.getSecurityQuestion());
        txtSecurityAnswer.setText(admin.getSecurityAnswer());

    }

    @FXML
    private void hanldeLogoutAction(ActionEvent event) throws IOException {

        Common common = new Common();
        common.nextStage("/javafx/demo/FXMLDocument.fxml", "Login Window ", true);

        Stage current = (Stage) txtPassword.getScene().getWindow();
        current.hide();
    }
}
