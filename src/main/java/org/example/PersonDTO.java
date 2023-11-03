package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
@RequiredArgsConstructor
@Getter
public class PersonDTO {

    private final PersonList personList;
    @Value("${app.writePath}")
    private String writePath;

    public void addPersonToPersonList(String personData){
        personList.getList().add(getPerson(personData));
    }

    public Person getPerson(String personData) {
        Person person = getPersonFromPersonList(personData);

        if (person.isEmpty()) {
            String[] data = personData.split(";");
            person.setFullName(data[0].trim());
            person.setPhoneNumber(data[1].trim());
            person.setEmail(data[2].trim());
            log.info("Added person: " + person.toString());
        } else log.info("Person whits name: " + person.getFullName() + " is exist!!");
        return person;
    }

    public void writePersonListToFile() {
        Path path = Paths.get(writePath);
        if(Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Files.write(path, personList.getPersonListToWrite().getBytes());
            log.info("All contacts saved.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePerson(String consoleDta) {
        Person person = getPersonFromPersonList(consoleDta);
        if(!person.isEmpty()) {
            personList.getList().remove(person);
            log.info("Removed person: " + person);
        } else System.out.println("Couldn't find person!!");
    }

    public Person getPersonFromPersonList(String consoleDta) {
        return personList.getList()
                .stream()
                .filter(p -> consoleDta.endsWith(p.getEmail()))
                .findFirst().orElse(new Person());
    }
}
