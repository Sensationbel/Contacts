package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
@PropertySource(value = "classpath:application.yaml", factory = YamlPropertySourceFactory.class)
public class Listener {

    private final PersonDTO personDTO;
    @Value("${app.consoleParam.fullData}")
    private String regexFullData;
    @Value("${app.consoleParam.email}")
    private String regexEmail;
    @Value("${app.consoleParam.exit}")
    private String exit;
    @Value("${app.consoleParam.print}")
    private String print;
    @Value("${app.consoleParam.write}")
    private String write;
    @Value("${app.consoleParam.info}")
    private String info;

    public void listenConsole() {
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            System.out.println(info);
            String consoleDta = scanner.nextLine();

            if (consoleDta.equals(exit)) {
                scanner.close();
                break;
            }
            log.info("Got data: " + consoleDta);
            checkConsoleDta(consoleDta);
        }

    }

    private void checkConsoleDta(String consoleDta) {
        if (consoleDta.matches(regexFullData)) {
            personDTO.addPersonToPersonList(consoleDta);
        } else if (consoleDta.matches(regexEmail)) {
            personDTO.deletePerson(consoleDta);
        } else if (consoleDta.equals(write)) {
            personDTO.writePersonListToFile();
        } else if (consoleDta.equals(print)) {
            personDTO.getPersonList().printPersonList();
        } else System.out.println("Data is not correct.");
    }
}
