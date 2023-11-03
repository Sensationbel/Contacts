package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InitContactsFromFile {
    @Value("${app.readPath}")
    private String fileName;

    @Autowired
    public void initContacts(PersonDTO personDTO) throws IOException {
        List<String> initData = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        List<Person> person = initData.stream().map(personDTO::getPerson).toList();
        personDTO.getPersonList().getList().addAll(person);
    }
}
