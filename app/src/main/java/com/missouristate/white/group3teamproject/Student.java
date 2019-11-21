package com.missouristate.white.group3teamproject;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String status;

    public Student(int newID, String newFName, String newLName, String newStatus){
        setID(newID);
        setFirstName(newFName);
        setLastName(newLName);
        setStatus(newStatus);
    }

    private void setStatus(String newStatus) {
        status = newStatus;
    }

    private void setLastName(String newLName) {
        lastName = newLName;
    }

    private void setFirstName(String newFName) {
        firstName = newFName;
    }

    private void setID(int newID) {
        id = newID;
    }

    public int getId(){

        return id;
    }


    public String getFirstName() {

        return firstName;

    }

    public String getLastName() {

        return lastName;

    }

    public String getStatus() {

        return status;

    }

    public String toString(){
        return id + "; " + firstName + "; " + lastName + "; " + status;
    }
}
