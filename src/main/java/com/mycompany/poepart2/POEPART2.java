/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart2;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author LEAG000
 */
public class POEPART2 {

    public static void main(String[] args) {
         LOGIN login = new LOGIN();
         

        // Registration
        JOptionPane.showMessageDialog(null, "Welcome! Please register to continue.");
        
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        String username = JOptionPane.showInputDialog("Enter username (must contain an underscore and be no more than 5 characters):");
        String password = JOptionPane.showInputDialog("Enter password (must be at least 8 characters, contain an uppercase letter, a number, and a special character):");

        // Register user
        String registrationMessage = login.registerUser(username, password, firstName, lastName);
        JOptionPane.showMessageDialog(null, registrationMessage);

        // User login
        boolean loggedIn = false;
        while (!loggedIn) {
            String loginUsername = JOptionPane.showInputDialog("Enter username:");
            String loginPassword = JOptionPane.showInputDialog("Enter password:");
            
            String loginMessage = login.returnLoginStatus(loginUsername, loginPassword);
            JOptionPane.showMessageDialog(null, loginMessage);
            
            if (loginMessage.startsWith("Welcome")) {
                loggedIn = true;
            }
        }

        // Main menu after successful login
        boolean running = true;
        while (running) {
            // Show vertically-aligned menu and validate input
            String menuMessage = "Welcome to EasyKanban\nSelect an option:\n1) Add tasks\n2) Show report\n3) Quit";
            String choiceInput = JOptionPane.showInputDialog(menuMessage);

            // Ensure the user inputs only numbers 1, 2, or 3
            if (choiceInput != null && choiceInput.matches("[1-3]")) {
                int choice = Integer.parseInt(choiceInput);
                switch (choice) {
                    case 1:
                        addTasks();
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Coming Soon");
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Leaving, EasyKanban. Thank you, Goodbye!");
                        running = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option. Please select 1, 2, or 3.");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1 and 3.");
            }
        }
    }

    private static void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks:"));

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            String taskDescription = JOptionPane.showInputDialog("Enter Task Description:");
            String developerDetails = JOptionPane.showInputDialog("Enter Developer Details (First and Last Name):");
            
            // Validate task duration format
            String taskDuration;
            while (true) {
                taskDuration = JOptionPane.showInputDialog("Enter Task Duration (e.g., 5h):");
                if (taskDuration != null && taskDuration.matches("\\d+h")) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid format. Please enter duration as a number followed by 'h' (e.g., 5h).");
                }
            }

           // Create a dropdown for task status selection
            String[] taskStatusOptions = {"To Do", "Doing", "Done"};
            String taskStatus = (String) JOptionPane.showInputDialog(
                    null,
                    "Select Task Status:",
                    "Task Status",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    taskStatusOptions,
                    taskStatusOptions[0]
            );

            // Validate task description length
            if (Tasks.checkTaskDescription(taskDescription)) {
                Tasks task = new Tasks(taskName, taskDescription, developerDetails, taskDuration, taskStatus);

                // Display task details after entry
                JOptionPane.showMessageDialog(null, "Task successfully captured\n" + task.printTaskDetails());
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
            }
        }

        // Display total accumulated task hours
        JOptionPane.showMessageDialog(null, "Total Task Hours: " + Tasks.returnTotalHours() + "h");
    }
}
