/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart2;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 *
 * @author LEAG000
 */
public class Tasks {
    private String taskName;
    private static int taskNumber = 0;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskID;
    private String taskStatus;

    // List to store all tasks created
    private static ArrayList<Tasks> taskList = new ArrayList<>();

    public Tasks(String taskName, String taskDescription, String developerDetails, String taskDuration, String taskStatus) {
        
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;

        // Parse the task duration from the input format (e.g., "5h")
        String[] durationArray = taskDuration.split("h");
        try {
            this.taskDuration = Integer.parseInt(durationArray[0]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid duration format. Please enter a valid number followed by 'h' (e.g., 5h).");
            this.taskDuration = 0;
        }
        
        this.taskStatus = taskStatus;

        // Generate a unique Task ID
        this.taskID = createTaskID();

        // Increment task number for unique Task ID
        taskNumber++;

        // Add task to the task list
        taskList.add(this);
    }

    public static boolean checkTaskDescription(String taskDescription) {
        if (taskDescription.length() < 50) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Task description is too long. Please enter a description with fewer than 50 characters.");
            return false;
        }
    }

    private String createTaskID() {
        // First two letters of task name in uppercase
        String firstTwoLetters = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();

        // Last three letters of the developer's last name in uppercase
        String[] developerNameArray = developerDetails.split(" ");
        String lastName = developerNameArray[developerNameArray.length - 1];
        String lastThreeLetters = lastName.length() >= 3 ? lastName.substring(lastName.length() - 3).toUpperCase() : lastName.toUpperCase();

        // Construct the Task ID
        return firstTwoLetters + ":" + taskNumber + ":" + lastThreeLetters;
    }

    public String printTaskDetails() {
        // Format task details for display
        return "Task Status: " + taskStatus +
               "\nDeveloper: " + developerDetails +
               "\nTask Number: " + taskNumber +
               "\nTask Name: " + taskName +
               "\nTask Description: " + taskDescription +
               "\nTask ID: " + taskID +
               "\nTask Duration: " + taskDuration + "h";
    }

    public static int returnTotalHours() {
        int totalHours = 0;
        for (Tasks task : taskList) {
            totalHours += task.taskDuration;
        }
        JOptionPane.showMessageDialog(null, "Total Task Hours: " + totalHours + "h");
        return totalHours;
    }
}
