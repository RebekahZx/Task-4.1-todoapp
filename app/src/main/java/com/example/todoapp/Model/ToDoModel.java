//package com.example.todoapp.Model;
//
//public class ToDoModel {
//    private String task;
//    private int id ,status;
//
//    public String getTask() {
//        return task;
//    }
//
//    public void setTask(String task) {
//        this.task = task;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//}
package com.example.todoapp.Model;



public class ToDoModel {
    private int id;
    private String task;
    private String description;
    private String dueDate;
    private int status;

    //start




    //end

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
