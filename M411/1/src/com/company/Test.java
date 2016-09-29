package com.company;

public class Test {
    private String firstName;
    private String lastName;

    Test(String firstName, String lastName){
        setFirstName(firstName);
        setLastName(lastName);
    }

    private void setFirstName(String firstName){
        this.firstName = firstName;
    }

    String getFirstName(){
        return firstName;
    }

    private void setLastName(String lastName){
        this.lastName = lastName;
    }

    String getLastName(){
        return lastName;
    }
}
