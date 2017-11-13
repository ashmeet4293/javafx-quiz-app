package pckgdatabase;

import com.sms.entity.Student;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDBUtils {

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Connection connection = DatabaseConnector.databaseConnector();
  String username;
    

    public boolean insertMarks(int marks ,String username){
        if (connection != null) {
            String query = "UPDATE students SET marks_obtained=? WHERE Uname=?";
            try {
                System.out.println("MARKS : "+marks+" USERNAME : "+username);
                preparedStatement = connection.prepareStatement(query);
               
                preparedStatement.setInt(1,marks );
                preparedStatement.setString(2, username);

                preparedStatement.executeUpdate();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
        
    }
    public boolean createStudent(Student student) {
        if (connection != null) {
            String query = "INSERT INTO Student (Namel, Address,sem,roll) VALUES (?,?,?,?)";
            try {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, student.getNamel());
                preparedStatement.setString(2, student.getAddress());
                preparedStatement.setInt(3, student.getSem());
                preparedStatement.setInt(4, student.getRoll());

                preparedStatement.execute();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public void fetchData() {
        try {
            String query = "SELECT * FROM Student";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("ID : " + resultSet.getInt("id"));
                System.out.println("NAME : " + resultSet.getString("namel"));
                System.out.println("ADDRESS : " + resultSet.getString("address"));
                System.out.println("Semester : " + resultSet.getInt("sem"));
                System.out.println("Roll  : " + resultSet.getInt("roll"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void fetchById(int id) {
        try {
            if (connection != null) {
                String query = "SELECT * FROM Student Where id=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println("ID : " + resultSet.getInt("id"));
                    System.out.println("NAME : " + resultSet.getString("namel"));
                    System.out.println("ADDRESS : " + resultSet.getString("address"));
                    System.out.println("Semester : " + resultSet.getInt("sem"));
                    System.out.println("Roll  : " + resultSet.getInt("roll"));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean updateData(Student student) {
        if (connection != null) {
            String query = "UPDATE Student Set namel=?, address=?, sem=?, roll=? where id = ?";
            try {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, student.getNamel());
                preparedStatement.setString(2, student.getAddress());
                preparedStatement.setInt(3, student.getSem());
                preparedStatement.setInt(4, student.getRoll());
                preparedStatement.setInt(5, student.getId());

                preparedStatement.executeUpdate();
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
                System.out.println("USERNAME : "+username);
                System.out.println("PASSWORD : "+password);
                String query= "SELECT * FROM students Where Uname=? AND Pass=? ";
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
