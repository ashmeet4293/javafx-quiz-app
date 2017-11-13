package pckgdatabase;

import com.sms.entity.Question;
import com.sms.entity.Student;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuestionDBUtils {

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Connection connection = DatabaseConnector.databaseConnector();
    List<Question> listOfQuestion = new ArrayList<Question>();

    public boolean createQuestion(Question question) {
        if (connection != null) {
            String query = "INSERT INTO question (Question, option1,option2,option3, option4, answer, subjectName) VALUES (?,?,?,?,?,?,?)";
            try {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, question.getQuestion());
                preparedStatement.setString(2, question.getOption1());
                preparedStatement.setString(3, question.getOption2());
                preparedStatement.setString(4, question.getOption3());
                preparedStatement.setString(5, question.getOption4());
                preparedStatement.setString(6, question.getAnswer());
                preparedStatement.setString(7, question.getSubjectName());
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

    public List<Question> fetchQuestionOfC() {
        try {
            if (connection != null) {
                String query = "SELECT * FROM C";
                preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setString(1, subjectName);

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int questionId = resultSet.getInt("questionId");
                    String question = resultSet.getString("Question");
                    String option1 = resultSet.getString("option1");
                    String option2 = resultSet.getString("option2");
                    String option3 = resultSet.getString("option3");
                    String option4 = resultSet.getString("option4");
                    String answer = resultSet.getString("answer");

                    System.out.println("QUESTIon ID : " + questionId);
//                    String subjectName1=resultSet.getString("subjectName");

                    listOfQuestion.add(new Question(questionId, question, option1, option2, option3, option4, answer));
                }
                return listOfQuestion;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

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
//    public Boolean loginValidation(String username, String password) {
//        if (connection != null) {
//            try {
//                System.out.println("USERNAME : " + username);
//                System.out.println("PASSWORD : " + password);
//                String query = "SELECT * FROM Student Where username=? AND password=? ";
//                preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setString(1, username);
//                preparedStatement.setString(2, password);
//                resultSet = preparedStatement.executeQuery();
//
//                while (resultSet.next()) {
//                    return true;
//
//                }
//
//            } catch (SQLException ex) {
//                Logger.getLogger(StudentDBUtils.class
//                        .getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//        return false;
//
//    }
}
