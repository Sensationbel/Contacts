package org.example;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class PersonList {

    private List<Person> list = new ArrayList<>();

    public void printPersonList(){
        list.forEach(person -> System.out.println(person.toString()));
    }

    public String getPersonListToWrite(){
        StringBuilder sb = new StringBuilder();
        list.forEach(person -> {
            sb.append(person.getFullName())
                    .append(";")
                    .append(person.getPhoneNumber())
                    .append(";")
                    .append(person.getEmail())
                    .append("\n");
        });
        return sb.toString();
    }
}
