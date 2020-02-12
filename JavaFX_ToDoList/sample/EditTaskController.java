package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class EditTaskController implements Initializable {

    @FXML
    private TextField taskTitle;

    @FXML
    private ComboBox<?> choosePriority;

    @FXML
    private DatePicker chooseDate;

    @FXML
    private TextArea textArea;

    @FXML
    private Button addTaskButton;

    @FXML
    void EditTask(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}