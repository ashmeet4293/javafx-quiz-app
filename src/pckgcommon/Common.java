package pckgcommon;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Common {

    public void nextStage(String fxml, String title, boolean resizable) throws IOException {
        Parent window = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = new Stage();
        Scene scene = new Scene(window);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.show();

    }

    public void alertMessage(String type, String title, String headerText, String content) {
        Alert alert = new Alert(Alert.AlertType.valueOf(type));
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.showAndWait();

    }
}