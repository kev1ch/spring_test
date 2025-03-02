package edu.spring_practice.services;

import edu.spring_practice.jpa.entities.Student;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class StudentFileListener {

    @SneakyThrows
    public List<Student> readFileToStudentList(String filename) {
        List<Student> result_list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(line -> {
                //TODO break line down and populate student object
                System.out.println(line);
                result_list.add(new Student());
            });
        }
        return result_list;
    }

    @Scheduled(fixedRate = 3000)
    public void scheduledFileReading() {
        //TODO filename from properties
        //TODO test for scheduledFileReading()

        // readFileToStudentList();
        System.out.println("call scheduledFileReading");
    }

}
