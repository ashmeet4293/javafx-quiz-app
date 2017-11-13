package pckgdatabase;

import com.sms.entity.Admin;
import com.sms.entity.Student;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminDBUtils {

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Connection connection = DatabaseConnector.databaseConnector();
    ObservableList<Admin> listOfAdmin=FXCollections.observableArrayList();

    public boolean createAdmin(Admin admin) {
        if (connection != null) {
            String query = "INSERT INTO admin (Admin_Name,password,security_question,Answer) VALUES (?,?,?,?)";
            try {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, admin.getAdminName());
                preparedStatement.setString(2, admin.getPassword());
                preparedStatement.setString(3, admin.getSecurityQuestion());
                preparedStatement.setString(4, admin.getSecurityAnswer());

                preparedStatement.execute();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
    
    public Boolean loginValidation(String username, String password) {
        if(connection!=null){
            try {
               
                String query= "SELECT Admin_Name,password FROM admin Where Admin_Name=? AND password=? ";
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    return true;
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return false;
        
    }
    

    public ObservableList<Admin> fetchData() {
        try {
            String query = "SELECT * FROM admin";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                
                
                Integer id= resultSet.getInt("Admin_ID");
                String adminName= resultSet.getString("Admin_Name");
                 String password = resultSet.getString("Password");
                 
                String securityQuestion= resultSet.getString("security_question");
                String securityAnswer= resultSet.getString("Answer");
                
                Admin admin=new Admin(id,adminName,password,securityQuestion,securityAnswer);
                listOfAdmin.add(admin);

            }
            return listOfAdmin;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
//
//    public void fetchById(int id) {
//        try {
//            if (connection != null) {
//                String query = "SELECT * FROM Student Where id=?";
//                preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setInt(1, id);
//
//                resultSet = preparedStatement.executeQuery();
//                while (resultSet.next()) {
//                    System.out.println("ID : " + resultSet.getInt("id"));
//                    System.out.println("NAME : " + resultSet.getString("namel"));
//                    System.out.println("ADDRESS : " + resultSet.getString("address"));
//                    System.out.println("Semester : " + resultSet.getInt("sem"));
//                    System.out.println("Roll  : " + resultSet.getInt("roll"));
//
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//    public boolean updateData(Student student) {
//        if (connection != null) {
//            String query = "UPDATE Student Set namel=?, address=?, sem=?, roll=? where id = ?";
//            try {
//
//                preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setString(1, student.getNamel());
//                preparedStatement.setString(2, student.getAddress());
//                preparedStatement.setInt(3, student.getSem());
//                preparedStatement.setInt(4, student.getRoll());
//                preparedStatement.setInt(5, student.getId());
//
//                preparedStatement.executeUpdate();
//                connection.close();
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }
//

}
