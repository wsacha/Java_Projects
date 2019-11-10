package sample;

import java.io.Serializable;
import java.time.LocalDate;

public class TaskDescription implements Serializable {
    String title;
    String priority;
    LocalDate date;
    String description;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescript(){return description;}
    public String getDescription(){return "Priority: " + priority + "\nExp date: " + date + "\nDescription: " +description;}

    public String getTitle() {
        return title;
    }

    public LocalDate getDate(){return date;}

    public String getPriority(){return priority;}

    public TaskDescription(){
        this.title=null;
        this.priority=null;
        this.date=null;
        this.description=null;
    }

    public TaskDescription(String title, String priority, LocalDate date, String description)
    {
        this.title=title;
        this.priority=priority;
        this.date=date;
        this.description=description;
    }
    public TaskDescription(TaskDescription tmp)
    {
        this.title=tmp.title;
        this.priority=tmp.priority;
        this.date=tmp.date;
        this.description=tmp.description;
    }

    @Override
    public String toString()
    {
        return this.title;
    }
}
