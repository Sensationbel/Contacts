package org.example;

import lombok.Data;

@Data
public class Person {

    private String fullName;
    private String email;
    private String phoneNumber;

    @Override
    public String toString() {
        return fullName + " | " + phoneNumber + " | " +email;
    }

    public boolean isEmpty(){
        return fullName == null
                && phoneNumber == null
                && email == null;
    }
}
