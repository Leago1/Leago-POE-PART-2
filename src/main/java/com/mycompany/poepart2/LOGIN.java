/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart2;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author LEAG000
 */
public class LOGIN {
    private String username, password;
    private static ArrayList<LOGIN> users = new ArrayList<>();

    public LOGIN() {
        // Default constructor
    }

    public LOGIN(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkUserName(String username) {
        if (username.length() <= 5 && username.contains("_")) {
            JOptionPane.showMessageDialog(null, "Username successfully captured.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters in length.");
            return false;
        }
    }

    public boolean checkPasswordComplexity(String password) {
        boolean containsUppercase = false;
        boolean containsNumber = false;
        boolean containsSpecialCharacter = false;

        if (password.length() >= 8) {
            for (char ch : password.toCharArray()) {
                if (Character.isUpperCase(ch)) containsUppercase = true;
                if (Character.isDigit(ch)) containsNumber = true;
                if (!Character.isLetterOrDigit(ch)) containsSpecialCharacter = true;
            }

            if (containsUppercase && containsNumber && containsSpecialCharacter) {
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "Password is too short.");
        return false;
    }

    public String registerUser(String username, String password, String name, String surname) {
        if (!checkUserName(username)) return "Username is not valid.";
        if (!checkPasswordComplexity(password)) return "Password is not complex enough.";

        LOGIN user = new LOGIN(username, password);
        users.add(user);
        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        for (LOGIN user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome, it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}
