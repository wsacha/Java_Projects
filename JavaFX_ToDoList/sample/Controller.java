package sample;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller extends ListCell<TaskDescription> implements Initializable {

    private FileChooser fileChooser;
    static ObservableList<TaskDescription> list= FXCollections.observableArrayList();
    static ObservableList<TaskDescription> progressList=FXCollections.observableArrayList();
    static ObservableList<TaskDescription> doneList=FXCollections.observableArrayList();
    @FXML
    public MenuItem CloseClick;
    @FXML
    public MenuItem AboutApp;
    @FXML
    public MenuItem EditProgressTask;
    @FXML
    public MenuItem DeleteProgressTask;
    @FXML
    public MenuItem DeleteNewTask;
    @FXML
    public MenuItem EditNewTask;
    @FXML
    public MenuItem EditDoneTask;
    @FXML
    public MenuItem DeleteDoneTask;


    @FXML
    private  ListView toDo=new ListView<>(list);

    @FXML
    private ListView<TaskDescription> inProgress=new ListView<>(progressList);

    @FXML
    private ListView<TaskDescription> doneTasks=new ListView<>(doneList);

    @FXML
    private Button addTask;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("/home/wojciech/Dokumenty"));


        toDo.setItems(list);
        inProgress.setItems(progressList);
        doneTasks.setItems(doneList);
        // Close button
        CloseClick.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    FileOutputStream fileOut = new FileOutputStream("/home/wojciech/Dokumenty/saved_state.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    serializeTaskList(list,out);
                    serializeTaskList(progressList,out);
                    serializeTaskList(doneList,out);
                    out.close();
                    fileOut.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
                Platform.exit();
            }
        });
        // Alert button
        AboutApp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About app");
                alert.setHeaderText(null);
                alert.setContentText("TODO-list Application by Sacha Wojciech");
                alert.showAndWait();
            }
        });
        //Tooltip
        toDo.setCellFactory(new Callback<ListView<TaskDescription>, ListCell<TaskDescription>>()
        {
            @Override
            public ListCell<TaskDescription> call(ListView<TaskDescription> listView) {
                return new createTooltip();
            }
        });

        inProgress.setCellFactory(new Callback<ListView<TaskDescription>, ListCell<TaskDescription>>()
        {
            @Override
            public ListCell<TaskDescription> call(ListView<TaskDescription> listView) {
                return new createTooltip();
            }
        });

        doneTasks.setCellFactory(new Callback<ListView<TaskDescription>, ListCell<TaskDescription>>()
        {
            @Override
            public ListCell<TaskDescription> call(ListView<TaskDescription> listView) {
                return new createTooltip();
            }
        });

        //left/right click to move task


        toDo.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                if (!toDo.getItems().isEmpty()) {
                    inProgress.getItems().add((TaskDescription) toDo.getItems().get(toDo.getFocusModel().getFocusedIndex()));
                    toDo.getItems().remove(toDo.getItems().get(toDo.getFocusModel().getFocusedIndex()));
                }
            }
        });

        inProgress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                if (!inProgress.getItems().isEmpty()) {
                    doneTasks.getItems().add(inProgress.getItems().get(inProgress.getFocusModel().getFocusedIndex()));
                    inProgress.getItems().remove(inProgress.getItems().get(inProgress.getFocusModel().getFocusedIndex()));
                }
            } else if (event.getCode() == KeyCode.LEFT) {
                if (!inProgress.getItems().isEmpty()) {
                    toDo.getItems().add(inProgress.getItems().get(inProgress.getFocusModel().getFocusedIndex()));
                    inProgress.getItems().remove(inProgress.getItems().get(inProgress.getFocusModel().getFocusedIndex()));
                }
            }
        });

        doneTasks.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                if (!doneTasks.getItems().isEmpty()) {
                    inProgress.getItems().add(doneTasks.getItems().get(doneTasks.getFocusModel().getFocusedIndex()));
                    doneTasks.getItems().remove(doneTasks.getItems().get(doneTasks.getFocusModel().getFocusedIndex()));
                }
            }
        });
        //Edit and Delete options
        toDo.setOnMouseClicked(event -> {

            if (toDo.getItems().isEmpty()) {
                EditNewTask.setVisible(false);
                DeleteNewTask.setVisible(false);
            } else {
                EditNewTask.setVisible(true);
                DeleteNewTask.setVisible(true);
            }

        });

        inProgress.setOnMouseClicked(event -> {

            if (inProgress.getItems().isEmpty()) {
                EditProgressTask.setVisible(false);
                DeleteProgressTask.setVisible(false);
            } else {
                EditProgressTask.setVisible(true);
                DeleteProgressTask.setVisible(true);
            }

        });

        doneTasks.setOnMouseClicked(event -> {

            if (doneTasks.getItems().isEmpty()) {
                EditDoneTask.setVisible(false);
                DeleteDoneTask.setVisible(false);
            } else {
                EditDoneTask.setVisible(true);
                DeleteDoneTask.setVisible(true);
            }

        });

        //Delete Action
        DeleteNewTask.setOnAction(event -> {
            if (!toDo.getItems().isEmpty()) {
                toDo.getItems().remove(toDo.getItems().get(toDo.getFocusModel().getFocusedIndex()));
            }
        });
        DeleteProgressTask.setOnAction(event -> {
            if (!inProgress.getItems().isEmpty()) {
                inProgress.getItems().remove(inProgress.getItems().get(inProgress.getFocusModel().getFocusedIndex()));
            }
        });

        DeleteDoneTask.setOnAction(event -> {
            if (!doneTasks.getItems().isEmpty()) {
                doneTasks.getItems().remove(doneTasks.getItems().get(doneTasks.getFocusModel().getFocusedIndex()));
            }
        });
    }

    @FXML
    void saveState(ActionEvent event) {
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Serialized data files (*.ser)","*.ser"));
        fileChooser.setInitialFileName("kanban_save.ser");
        File dir = fileChooser.showSaveDialog(null);
        if(dir!=null){
            try{
                FileOutputStream fileOut = new FileOutputStream(dir.getAbsolutePath());
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                serializeTaskList(list,out);
                serializeTaskList(progressList,out);
                serializeTaskList(doneList,out);
                out.close();
                fileOut.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            System.out.println("Serialization success");
        }
    }

    private void serializeTaskList(ObservableList<TaskDescription> serializeList, ObjectOutputStream out){
        //convert to ArrayList
        ArrayList<TaskDescription> alist = new ArrayList<>(list.size());
        alist.addAll(serializeList);
        //serialize
        try{
            out.writeObject(alist);
            System.out.println("List serialized");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void deserializeTaskList(ObjectInputStream in, ObservableList<TaskDescription> deserializeList){
        //deserialize
        ArrayList<TaskDescription> alist = null;
        try{
            alist = (ArrayList<TaskDescription>) in.readObject();
            System.out.println("List serialized data opened");
        }catch(IOException | ClassNotFoundException e){
            if(e instanceof ClassNotFoundException){
                System.err.println("Deserialization: class not found");
            }
            e.printStackTrace();
        }
        //convert to ObservableList<Task>
        if(alist!=null & !alist.isEmpty()){
            deserializeList.clear();
            deserializeList.addAll(alist);
        }
    }

    @FXML
    void loadState(ActionEvent event) {
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Serialized data files (*.ser)","*.ser"));
        File dir = fileChooser.showOpenDialog(null);
        if(dir!=null){
            try{
                FileInputStream fileIn = new FileInputStream(dir.getAbsolutePath());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                deserializeTaskList(in,list);
                deserializeTaskList(in,progressList);
                deserializeTaskList(in,doneList);
                in.close();
                fileIn.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            System.out.println("Deserialization success");
        }
    }

    @FXML
    void exportData(ActionEvent event) {
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV files (*.csv)","*.csv"));
        fileChooser.setInitialFileName("kanban.csv");
        File dir = fileChooser.showSaveDialog(null);
        String path = dir.getAbsolutePath().replaceAll(".csv","");
        try{
            FileWriter writer = new FileWriter(path+"_1todo.csv");
            writer.append(taskListToCSV(list));writer.flush();
            writer = new FileWriter(path+"_2open.csv");
            writer.append(taskListToCSV(progressList));writer.flush();
            writer = new FileWriter(path+"_3done.csv");
            writer.append(taskListToCSV(doneList));writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("CSV export succesful");
    }

    private String taskListToCSV(ObservableList<TaskDescription> tlist){
        StringBuilder sb = new StringBuilder();
        sb.append("Title,Priority,Expiration,Description");
        for(TaskDescription t: tlist){
            sb.append("\n");
            sb.append(t.getTitle());                    sb.append(',');
            sb.append(t.getPriority());                 sb.append(',');
            sb.append(t.getDate().toString());    sb.append(',');
            sb.append(t.getDescript());
        }
        return sb.toString();
    }

    @FXML
    void importData(ActionEvent event) {
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV files (*.csv)","*.csv"));
        List<File> dirs = fileChooser.showOpenMultipleDialog(null);
        int loaded_count = 0;
        for(File f: dirs){
            String path = f.getPath();
            System.out.println(path);
            System.out.println(f.getAbsolutePath());
            if((path.substring(path.lastIndexOf("/"))).contains("todo")){
                list.clear();
                list.addAll(taskListFromCSV(f.getAbsolutePath()));
                loaded_count++;
            }
            if((path.substring(path.lastIndexOf("/"))).contains("open")){
                progressList.clear();
                progressList.addAll(taskListFromCSV(f.getAbsolutePath()));
                loaded_count++;
            }
            if((path.substring(path.lastIndexOf("/"))).contains("done")){
                doneList.clear();
                doneList.addAll(taskListFromCSV(f.getAbsolutePath()));
                loaded_count++;
            }
        }
        System.out.println("Import: Succesfully loaded "+loaded_count+" files.");
    }

    private ObservableList<TaskDescription> taskListFromCSV(String filepath){
        ObservableList<TaskDescription> tlist = FXCollections.observableArrayList();
        String line;
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filepath));
            br.readLine();
            while((line = br.readLine()) != null){
                TaskDescription t = new TaskDescription();
                String[] vals = line.split(",");
                t.setTitle(vals[0]);
                t.setPriority(vals[1]);
                t.setDate(LocalDate.parse(vals[2]));
                try{
                    t.setDescription(vals[3]);
                }catch(IndexOutOfBoundsException e){
                    t.setDescription("");
                }
                tlist.add(t);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tlist;
    }

    @FXML
    void addNewTask(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewTask.fxml"));
        Parent root= (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600, 500));
        stage.show();
    }
    static void addToList(TaskDescription tmp)
    {

        TaskDescription newTask=new TaskDescription(tmp);
        list.add(newTask);
        System.out.println(list);
    }

    static class createTooltip extends ListCell<TaskDescription> {
        @Override
        public void updateItem(TaskDescription item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setTooltip(null);
            } else {
                setText(getItem().getTitle());
                Tooltip tooltip = new Tooltip();
                tooltip.setText(getItem().getDescription());
                setTooltip(tooltip);
            }
        }
    }
}
