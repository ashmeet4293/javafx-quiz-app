/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quiz.quizwindow;

import com.sms.entity.Question;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.demo.FXMLDocumentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import pckgdatabase.QuestionDBUtils;
import pckgcommon.Common;
import pckgdatabase.StudentDBUtils;

/**
 * FXML Controller class
 *
 * @author ashmeet
 */
public class QuizWindowController extends FXMLDocumentController implements Initializable {

    @FXML
    private RadioButton rdOpt1;
    @FXML
    private RadioButton rdOpt2;
    @FXML
    private RadioButton rdOpt3;
    @FXML
    private RadioButton rdOpt4;
    @FXML
    private Label lblQuestion;

    QuestionDBUtils questionDBUtils;
    StudentDBUtils studentDBUtils;
    Question question;
    ToggleGroup toggleGroup;
    Common common;
    @FXML
    private Label lblSTatus;
    @FXML
    private Button btnLoad;
    @FXML
    private Button btnSubmit;

    int i = 0;
    int marks = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        toggleGroup = new ToggleGroup();
        rdOpt1.setToggleGroup(toggleGroup);
        rdOpt2.setToggleGroup(toggleGroup);
        rdOpt3.setToggleGroup(toggleGroup);
        rdOpt4.setToggleGroup(toggleGroup);

        // TODO
    }

    @FXML
    private void hanldeSubmitButtonAction(ActionEvent event) throws IOException {

        btnLoad.setDisable(true);
        List<Question> questions = loadQuestion();
        question = new Question();
        question = questions.get(i);

//         FXMLDocumentController loginDetails=new FXMLDocumentController();
        Stage current = (Stage) lblQuestion.getScene().getWindow();
        String username1= current.getTitle();
        
        String correctAnswer = question.getAnswer();
        RadioButton button = (RadioButton) toggleGroup.getSelectedToggle();
        String answerSelectedByUser = button.getText();

        if (correctAnswer.equals(answerSelectedByUser)) {
            lblSTatus.setText("Your Answer is correct");
            marks++;

        } else {
            lblSTatus.setText("You Answer is incorrect and correct answer is : " + correctAnswer);
        }
        if (i == 10) {
            lblSTatus.setText("Your total marks is:" + marks + "out of 10");
            studentDBUtils = new StudentDBUtils();
//        
            if(studentDBUtils.insertMarks(marks,username1)){
                System.out.println("MArks Inserted succesffully");
            }
            btnSubmit.setDisable(true);

        }
        i++;
        question = questions.get(i);
        lblQuestion.setText(question.getQuestion());
        rdOpt1.setText(question.getOption1());
        rdOpt2.setText(question.getOption2());
        rdOpt3.setText(question.getOption3());
        rdOpt4.setText(question.getOption4());

    }

    @FXML
    private void hanldeLoadButtonAction(ActionEvent event) {
        List<Question> questions = loadQuestion();
        question = new Question();

        question = questions.get(0);

        lblQuestion.setText(question.getQuestion());
        rdOpt1.setText(question.getOption1());
        rdOpt2.setText(question.getOption2());
        rdOpt3.setText(question.getOption3());
        rdOpt4.setText(question.getOption4());

    }

    private List<Question> loadQuestion() {
        List<Question> questions;
        questionDBUtils = new QuestionDBUtils();

        questions = questionDBUtils.fetchQuestionOfC();
        if (questions == null) {
            System.out.println("CAnnot fecth question");
        }
        return questions;
    }
}
