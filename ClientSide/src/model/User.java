package model;

import java.rmi.RemoteException;
import java.util.Random;

public class User implements java.io.Serializable{
    private String studentNumber;
    private String password;

    public User(String studentNumber, String password) {
        this.studentNumber = studentNumber;
        this.password = password;
    }
    public String getStudentNumber() {
        return studentNumber;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return studentNumber + " " + password;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
