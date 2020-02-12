package sample;


import java.lang.String;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class NewTaskController implements Initializable {



    @FXML
    private TextField taskTitle;

    @FXML
    private ComboBox<String> choosePriority;

    @FXML
    private DatePicker chooseDate;

    @FXML
    private TextArea textArea;

    @FXML
    private Button addTaskButton;

    @FXML
    void addTask(ActionEvent event) {

        TaskDescription tmp =new TaskDescription(taskTitle.getText(),choosePriority.getValue(),chooseDate.getValue(),textArea.getText());
        System.out.println(tmp);
        Controller.addToList(tmp);
        taskTitle.clear();
        textArea.clear();
        chooseDate.setValue(LocalDate.now());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    chooseDate.setValue(LocalDate.now());
    choosePriority.getSelectionModel().selectFirst();
    }
}
