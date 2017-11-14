/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admindashboard;

import com.sms.entity.Admin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
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

    @FXML
    private TextField txtSearchField;

    private Admin admin;
    private AdminDBUtils adminDBUtils;
    FileChooser fileChooser;
    File file;
    FileInputStream fis;
    private Image image;
//   ObservableList<Admin> adminList ;

    ObservableList<Admin> adminList = FXCollections.observableArrayList();

    FilteredList<Admin> filteredData;
//    = new FilteredList<>(adminList, e -> true);
    @FXML
    private Button btnBrowse;
    @FXML
    private ImageView imgView;
    @FXML
    private TableColumn<Admin, FileInputStream> colImages;

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
    private void handleSaveAction(ActionEvent event) throws FileNotFoundException {
        admin = new Admin();
        adminDBUtils = new AdminDBUtils();
        String securityQuestion = cmbSecurityQuestions.getSelectionModel().getSelectedItem();

        admin.setAdminName(txtName.getText());
        admin.setPassword(txtPassword.getText());
        admin.setSecurityQuestion(securityQuestion);
        admin.setSecurityAnswer(txtSecurityAnswer.getText());
        fis = new FileInputStream(file);
        admin.setFis(fis);

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
        imgView.setImage(null);
    }

    @FXML
    private void hanldeLoadAction(ActionEvent event) {

        admin = new Admin();
        adminDBUtils = new AdminDBUtils();
        adminList = adminDBUtils.fetchData();
        if ((adminList) != null) {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colAdminName.setCellValueFactory(new PropertyValueFactory<>("adminName"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            colSecurityQuestion.setCellValueFactory(new PropertyValueFactory<>("securityQuestion"));
            colSecurityAnswer.setCellValueFactory(new PropertyValueFactory<>("securityAnswer"));
//            colImages.setCellFactory(new PropertyValueFactory<>("fis"));

            tblAdminData.setItems(adminList);
            filteredData = new FilteredList<>(adminList, e -> true);

        } else {
            System.out.println("No data found ");
        }
    }

    @FXML
    private void hanldeMouseClickeAction(MouseEvent event) throws IOException {
        showTableDataOnFields();

    }

    void showTableDataOnFields() throws FileNotFoundException, IOException {
        Admin admin = (Admin) tblAdminData.getSelectionModel().getSelectedItem();
        txtId.setText("" + admin.getId());
        txtName.setText(admin.getAdminName());
        txtPassword.setText(admin.getPassword());
        cmbSecurityQuestions.setValue(admin.getSecurityQuestion());
        txtSecurityAnswer.setText(admin.getSecurityAnswer());
        InputStream is=admin.getFis();
//        is = rs.getBinaryStream("image");

//        if (is != null) {
//            OutputStream os = new FileOutputStream(new File("photo.jpg"));
//            byte[] content = new byte[1024];
//            while ((is.read(content) > 0)) {
//                os.write(content);
//            }
//            is.close();
//            image = new Image("file:photo.jpg");
//            imgView.setImage(image);
//        } else {
//            imgView.setImage(null);
//        }

    }

    @FXML
    private void hanldeLogoutAction(ActionEvent event) throws IOException {

        Common common = new Common();
        common.nextStage("/javafx/demo/FXMLDocument.fxml", "Login Window ", true);

        Stage current = (Stage) txtPassword.getScene().getWindow();
        current.hide();
    }

    @FXML
    private void hanldeKeyReleased(KeyEvent event) {
        txtSearchField.textProperty().addListener((observalbleValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Admin>) admin1 -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                System.out.println("ADMIN NEW VALUE : " + lowerCaseFilter);
//                String adminId=""+admin.getId();
                Integer id = admin1.getId();
                if (id.toString().contains(newValue)) {
                    return true;
                }
//                if (adminId.contains(newValue)) {
//                    return true;
//                }
                if (admin1.getAdminName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
//                if (admin.().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                }
                return false;
            });
        });
        SortedList<Admin> sortedData = new SortedList<>(filteredData);
        System.out.println("Datas are : " + filteredData);
        sortedData.comparatorProperty().bind(tblAdminData.comparatorProperty());
        tblAdminData.setItems(sortedData);
    }

    @FXML
    private void hanldeBrowseAction(ActionEvent event) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.gif", "*.png", "*.JPG")
        );
        Stage current = (Stage) txtSearchField.getScene().getWindow();
        file = fileChooser.showOpenDialog(current);

        if (file != null) {
            image = new Image(file.toURI().toString());
            imgView.setImage(image);
        }

    }
}
